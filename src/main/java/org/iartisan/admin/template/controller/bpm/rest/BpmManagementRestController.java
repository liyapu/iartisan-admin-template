package org.iartisan.admin.template.controller.bpm.rest;

import org.iartisan.admin.template.service.bpm.BpmWorkflowManagement;
import org.iartisan.admin.template.service.bpm.entity.LeaveEntity;
import org.iartisan.runtime.bean.Pagination;
import org.iartisan.runtime.web.WebR;
import org.iartisan.runtime.web.contants.ReqContants;
import org.iartisan.runtime.web.controller.BaseController;
import org.iartisan.runtime.web.controller.ISupportRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
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
@RequestMapping("bpm/workflow")
public class BpmManagementRestController extends BaseController implements ISupportRestController<LeaveEntity> {

    @Autowired
    private BpmWorkflowManagement workflowManagement;

    @Override
    public WebR deleteData(String s) {
        return null;
    }

    @Override
    public WebR modifyData(LeaveEntity leaveEntity) {
        return null;
    }

    @Override
    @PostMapping("start/" + ReqContants.REQ_ADD_DATA)
    public WebR addData(LeaveEntity leaveEntity) {
        WebR webR = new WebR();
        Map<String, Object> variables = new HashMap<>();
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
    @PostMapping("start/owner/" + ReqContants.REQ_QUERY_PAGE_DATA)
    public WebR queryOwnerPageTasks(Pagination page) {
        WebR r = new WebR();
        return r;
    }

    @ResponseBody
    @RequestMapping(value = "/getProcessTrace")
    public WebR getProcessTrace(String processInstanceId, HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        WebR r = new WebR();
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }
}
