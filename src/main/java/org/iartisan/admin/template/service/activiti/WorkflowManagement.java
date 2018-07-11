package org.iartisan.admin.template.service.activiti;

import org.activiti.engine.FormService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
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

    public void startProcess(String processId, String staffId, Map<String, String> variables) {
        identityService.setAuthenticatedUserId(staffId);
        ProcessDefinition definition = repositoryService.createProcessDefinitionQuery().processDefinitionTenantId(processId).singleResult();
        ProcessInstance processInstance = formService.submitStartFormData(definition.getId(), variables);
        logger.info("===>启动流程{}", processInstance);
    }
}
