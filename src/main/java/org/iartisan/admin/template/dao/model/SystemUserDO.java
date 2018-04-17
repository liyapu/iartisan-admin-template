package org.iartisan.admin.template.dao.model;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.ibatis.type.Alias;

/**
 * system_user 表模型
 *
 * @author King
 */
@Alias("systemUser")
@TableName(value = "system_user")
public class SystemUserDO {

    /**
     * 列名: USER_ID
     * 备注: 主键
     */
    @TableId("USER_ID")
    private String userId;

    /**
     * 列名: USER_NAME
     * 备注: 用户名
     */
    @TableField("USER_NAME")
    private String userName;

    /**
     * 列名: USER_PWD
     * 备注: 密码
     */
    @TableField("USER_PWD")
    private String userPwd;

    /**
     * 列名: STATUS
     * 备注: 员工状态
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


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}