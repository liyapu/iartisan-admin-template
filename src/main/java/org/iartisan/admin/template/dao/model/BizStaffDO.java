package org.iartisan.admin.template.dao.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;

/**
* biz_staff 表模型
* @author King
*/
@TableName(value = "biz_staff")
public class BizStaffDO {

/**
* 列名: STAFF_ID
* 备注: 主键
*/
@TableId("STAFF_ID")
private String staffId;

/**
* 列名: STAFF_NAME
* 备注: 员工姓名
*/
@TableField("STAFF_NAME")
private String staffName;

/**
* 列名: STAFF_DEPT
* 备注: 员工所在部门
*/
@TableField("STAFF_DEPT")
private String staffDept;

/**
* 列名: STATUS
* 备注: 数据状态
*/
@TableField("STATUS")
private String status;

/**
* 列名: CREATE_TIME
* 备注: 创建时间
*/
@TableField("CREATE_TIME")
private Date createTime;

/**
* 列名: UPDATE_TIME
* 备注: 更新时间
*/
@TableField("UPDATE_TIME")
private Date updateTime;


public String getStaffId(){
return staffId;
}

public void setStaffId(String staffId){
this.staffId = staffId;
}

public String getStaffName(){
return staffName;
}

public void setStaffName(String staffName){
this.staffName = staffName;
}

public String getStaffDept(){
return staffDept;
}

public void setStaffDept(String staffDept){
this.staffDept = staffDept;
}

public String getStatus(){
return status;
}

public void setStatus(String status){
this.status = status;
}

public Date getCreateTime(){
return createTime;
}

public void setCreateTime(Date createTime){
this.createTime = createTime;
}

public Date getUpdateTime(){
return updateTime;
}

public void setUpdateTime(Date updateTime){
this.updateTime = updateTime;
}

}