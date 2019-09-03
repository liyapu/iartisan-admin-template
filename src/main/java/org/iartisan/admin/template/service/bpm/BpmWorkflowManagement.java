package org.iartisan.admin.template.service.bpm;


import org.flowable.engine.*;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.iartisan.admin.template.service.bpm.entity.TaskEntity;
import org.iartisan.runtime.bean.PageWrapper;
import org.iartisan.runtime.bean.Pagination;
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
public class BpmWorkflowManagement {

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
}
