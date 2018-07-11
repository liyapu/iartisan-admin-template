package org.iartisan.admin.template.controller.activiti.rest;

import org.iartisan.admin.template.service.activiti.WorkflowManagement;
import org.iartisan.admin.template.service.entity.LeaveEntity;
import org.iartisan.runtime.web.WebR;
import org.iartisan.runtime.web.contants.ReqContants;
import org.iartisan.runtime.web.controller.BaseController;
import org.iartisan.runtime.web.controller.ISupportRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 流程管理
 *
 * @author King
 * @since 2018/6/28
 */
@RestController
@RequestMapping("activiti/workflow")
public class WorkflowManagementRestController extends BaseController implements ISupportRestController<LeaveEntity> {

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
        WebR webR = new WebR();
        Map<String, String> variables = new HashMap<>();
        variables.put("beginTime", leaveEntity.getBeginTime());
        variables.put("endTime", leaveEntity.getEndTime());
        variables.put("days", leaveEntity.getDays());
        variables.put("reason", leaveEntity.getReason());
        workflowManagement.startProcess(leaveEntity.getProcessId(), getCustId(), variables);
        return webR;
    }

    /**
     * 分页查询自己发起的任务
     *
     * @return
     */
    @PostMapping("owner/" + ReqContants.REQ_QUERY_PAGE_DATA)
    public WebR queryOwnerPageTasks() {
        WebR r = new WebR();
        return r;
    }
}
