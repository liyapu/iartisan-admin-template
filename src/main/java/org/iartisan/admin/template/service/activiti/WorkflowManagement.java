package org.iartisan.admin.template.service.activiti;

import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.beanutils.PropertyUtils;
import org.iartisan.admin.template.service.activiti.entity.DeploymentEntity;
import org.iartisan.admin.template.service.activiti.entity.TaskEntity;
import org.iartisan.runtime.bean.Pagination;
import org.iartisan.runtime.bean.PageWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * workflow management
 *
 * @author King
 * @since 2018/7/11
 */
@Service
public class WorkflowManagement {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected IdentityService identityService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    protected FormService formService;

    @Autowired
    protected HistoryService historyService;

    @Autowired
    protected TaskService taskService;

    @Autowired
    private RuntimeService runtimeService;

    public void startProcess(String processId, String staffId, Map<String, String> variables) {
        identityService.setAuthenticatedUserId(staffId);
        ProcessDefinition definition = repositoryService.createProcessDefinitionQuery().deploymentId(processId).singleResult();
        ProcessInstance processInstance = formService.submitStartFormData(definition.getId(), variables);
        logger.info("===>启动流程{}", processInstance);
    }

    public PageWrapper<TaskEntity> getPageTasks(String startedBy, Pagination page) {
        List<HistoricProcessInstance> result = historyService.createHistoricProcessInstanceQuery().startedBy(startedBy)
                .listPage((page.getPageIndex() - 1) * page.getPageSize(), page.getPageSize());
        long total = historyService.createHistoricProcessInstanceQuery().startedBy(startedBy).count();
        List<TaskEntity> dataList = new ArrayList<>();
        result.forEach(v -> {
                    //查询当前节点名称
                    Task task = taskService.createTaskQuery().processInstanceId(v.getId()).active().singleResult();
                    TaskEntity entity = new TaskEntity();
                    entity.setInstId(v.getId());
                    //todo 查询当前节点处理人
                    if (null != task) {
                        entity.setTaskNodeName(task.getName());
                    }
                    dataList.add(entity);
                }
        );
        page.setTotalRecords((int) total);
        page.setPageIndex(page.getPageIndex() + 1);
        PageWrapper<TaskEntity> resultPage = new PageWrapper<>(page);
        resultPage.setRows(dataList);
        return resultPage;
    }

    public InputStream getProcessDefinitionImg(String processInstanceId) {
        HistoricProcessInstance processInstance = this.getHistoricInstanceById(processInstanceId);
        //ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processInstance.getProcessDefinitionId())
                .singleResult();
        String resourceName = processDefinition.getDiagramResourceName();
        InputStream resourceAsStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), resourceName);
        return resourceAsStream;
    }


    public HistoricProcessInstance getHistoricInstanceById(String instId) {
        return historyService.createHistoricProcessInstanceQuery().processInstanceId(instId).singleResult();
    }

    public List<Map<String, Object>> getProcessTrace(String processInstanceId) throws Exception {
        Execution execution = runtimeService.createExecutionQuery().executionId(processInstanceId).singleResult();//执行实例
        List<Map<String, Object>> activityInfos = new ArrayList<>();
        if (null != execution) {
            Object property = PropertyUtils.getProperty(execution, "activityId");
            String activityId = "";
            if (property != null) {
                activityId = property.toString();
            }
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
            ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(processInstance.getProcessDefinitionId());
            List<ActivityImpl> activitiList = processDefinition.getActivities();//获得当前任务的所有节点
            for (ActivityImpl activity : activitiList) {
                boolean currentActiviti = false;
                String id = activity.getId();
                // 当前节点
                if (id.equals(activityId)) {
                    currentActiviti = true;
                }
                Map<String, Object> activityImageInfo = packageSingleActivitiInfo(activity, currentActiviti);
                activityInfos.add(activityImageInfo);
            }
        }
        return activityInfos;
    }

    /**
     * 封装输出信息，包括：当前节点的X、Y坐标、变量信息、任务类型、任务描述
     *
     * @param activity
     * @param currentActiviti
     * @return
     */
    private Map<String, Object> packageSingleActivitiInfo(ActivityImpl activity,
                                                          boolean currentActiviti) throws Exception {
        Map<String, Object> vars = new HashMap<>();
        Map<String, Object> activityInfo = new HashMap<>();
        activityInfo.put("currentActiviti", currentActiviti);
        setPosition(activity, activityInfo);
        setWidthAndHeight(activity, activityInfo);
        Map<String, Object> properties = activity.getProperties();
        vars.put("任务类型", WorkflowUtil.parseToZhType(properties.get("type").toString()));
        ActivityBehavior activityBehavior = activity.getActivityBehavior();
        logger.debug("activityBehavior={}", activityBehavior);
        vars.put("节点说明", properties.get("documentation"));
        String description = activity.getProcessDefinition().getDescription();
        vars.put("描述", description);
        logger.debug("trace variables: {}", vars);
        activityInfo.put("vars", vars);
        return activityInfo;
    }

    /**
     * 设置坐标位置
     *
     * @param activity
     * @param activityInfo
     */
    private void setPosition(ActivityImpl activity, Map<String, Object> activityInfo) {
        activityInfo.put("x", activity.getX());
        activityInfo.put("y", activity.getY());
    }

    /**
     * 设置宽度、高度属性
     *
     * @param activity
     * @param activityInfo
     */
    private void setWidthAndHeight(ActivityImpl activity, Map<String, Object> activityInfo) {
        activityInfo.put("width", activity.getWidth());
        activityInfo.put("height", activity.getHeight());
    }
}
