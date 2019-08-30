package org.iartisan.admin.template.service.bpm;


import org.flowable.engine.*;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
