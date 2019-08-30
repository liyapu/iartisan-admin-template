package org.iartisan.admin.template.service.bpm.entity;

/**
 * <p>
 * task entity
 *
 * @author King
 * @since 2018/7/12
 */
public class TaskEntity {

    private String instId;//流程ID

    private String taskNodeName;//当前任务节点名称


    public String getTaskNodeName() {
        return taskNodeName;
    }

    public void setTaskNodeName(String taskNodeName) {
        this.taskNodeName = taskNodeName;
    }

    public String getInstId() {
        return instId;
    }

    public void setInstId(String instId) {
        this.instId = instId;
    }
}
