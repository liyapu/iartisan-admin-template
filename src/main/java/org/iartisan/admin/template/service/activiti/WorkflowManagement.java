package org.iartisan.admin.template.service.activiti;

import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.iartisan.runtime.bean.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void getPageTasks(String startedBy, Page page) {
        List<HistoricProcessInstance> result = historyService.createHistoricProcessInstanceQuery().startedBy(startedBy)
                .listPage((page.getCurrPage() - 1) * page.getPageSize(), page.getPageSize());
        long total = historyService.createHistoricProcessInstanceQuery().startedBy(startedBy).count();
        result.forEach(v -> {
                    //查询当前节点名称
                    Task task = taskService.createTaskQuery().processInstanceId(v.getId()).active().singleResult();
                    //查询当前节点处理人
                }
        );


    }
}
