package org.iartisan.admin.template.controller.activiti.rest;

import org.iartisan.admin.template.service.activiti.WorkflowManagement;
import org.iartisan.admin.template.service.entity.LeaveEntity;
import org.iartisan.runtime.web.WebR;
import org.iartisan.runtime.web.contants.ReqContants;
import org.iartisan.runtime.web.controller.BaseController;
import org.iartisan.runtime.web.controller.ISupportRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * 流程管理
 *
 * @author King
 * @since 2018/6/28
 */
@Controller
@RequestMapping("activiti/workflowStart")
public class WorkflowStartRestController extends BaseController implements ISupportRestController<LeaveEntity> {

    @Autowired
    private WorkflowManagement workflowManagement;

    @Override
    public WebR deleteData(String s) {
        return null;
    }

    @Override
    public WebR modifyData(LeaveEntity leaveEntity) {
        return null;
    }

    @PostMapping(ReqContants.REQ_ADD_DATA)
    public WebR addData(LeaveEntity leaveEntity) {
        workflowManagement.startProcess(null, null, null);
        return null;
    }
}
