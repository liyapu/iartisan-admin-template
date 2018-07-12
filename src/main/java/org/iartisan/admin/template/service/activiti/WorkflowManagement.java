package org.iartisan.admin.template.service.activiti;

import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.iartisan.admin.template.service.activiti.entity.DeploymentEntity;
import org.iartisan.admin.template.service.activiti.entity.TaskEntity;
import org.iartisan.runtime.bean.Page;
import org.iartisan.runtime.bean.PageWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public void startProcess(String processId, String staffId, Map<String, String> variables) {
        identityService.setAuthenticatedUserId(staffId);
        ProcessDefinition definition = repositoryService.createProcessDefinitionQuery().deploymentId(processId).singleResult();
        ProcessInstance processInstance = formService.submitStartFormData(definition.getId(), variables);
        logger.info("===>启动流程{}", processInstance);
    }

    public PageWrapper<TaskEntity> getPageTasks(String startedBy, Page page) {
        List<HistoricProcessInstance> result = historyService.createHistoricProcessInstanceQuery().startedBy(startedBy)
                .listPage((page.getCurrPage() - 1) * page.getPageSize(), page.getPageSize());
        long total = historyService.createHistoricProcessInstanceQuery().startedBy(startedBy).count();
        List<TaskEntity> dataList = new ArrayList<>();
        result.forEach(v -> {
                    //查询当前节点名称
                    Task task = taskService.createTaskQuery().processInstanceId(v.getId()).active().singleResult();
                    TaskEntity entity = new TaskEntity();
                    //todo 查询当前节点处理人
                    if (null != task) {
                        entity.setTaskNodeName(task.getName());
                    }
                    dataList.add(entity);
                }
        );
        page.setTotalRecords((int) total);
        page.setCurrPage(page.getCurrPage() + 1);
        PageWrapper<TaskEntity> resultPage = new PageWrapper<>(page);
        resultPage.setData(dataList);
        return resultPage;
    }
}
