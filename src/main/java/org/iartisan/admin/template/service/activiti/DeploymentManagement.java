package org.iartisan.admin.template.service.activiti;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.apache.commons.lang3.StringUtils;
import org.iartisan.admin.template.service.entity.DeploymentEntity;
import org.iartisan.runtime.bean.Page;
import org.iartisan.runtime.bean.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 流程部署
 *
 * @author King
 * @since 2018/6/28
 */
@Service
public class DeploymentManagement {

    @Autowired
    private RepositoryService repositoryService;

    public PageWrapper<DeploymentEntity> queryDeploymentsPage(Page page) {
        long total = repositoryService.createDeploymentQuery().count();
        List<Deployment> result = repositoryService.createDeploymentQuery().orderByDeploymenTime().asc().listPage(
                (page.getCurrPage() - 1) * page.getPageSize()
                , page.getPageSize());
        page.setTotalRecords((int) total);
        page.setCurrPage(page.getCurrPage() + 1);
        PageWrapper<DeploymentEntity> resultPage = new PageWrapper<>(page);
        List<DeploymentEntity> deploymentEntities = new ArrayList<>();
        result.forEach(v -> {
            DeploymentEntity entity = new DeploymentEntity();
            entity.setId(v.getId());
            entity.setName(v.getName());
            entity.setCategory(v.getCategory());
            entity.setDeploymentTime(v.getDeploymentTime());
            entity.setTenantId(v.getTenantId());
            deploymentEntities.add(entity);
        });
        resultPage.setData(deploymentEntities);
        return resultPage;
    }



    public void deploy(String fileName, InputStream inputStream) {
        repositoryService.createDeployment().addInputStream(fileName, inputStream).name(fileName).deploy();
    }

    public void delete(String deploymentId) {
        repositoryService.deleteDeployment(deploymentId);
    }

    //挂起流程
    public void suspend(String deploymentId) {
        //通过deloyemntId 查询defineId
        Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(deploymentId).singleResult();

        //repositoryService.suspendProcessDefinitionById();
    }
}
