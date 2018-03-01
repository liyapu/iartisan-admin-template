package org.iartisan.admin.template.authentication.support.dbm.model;

    import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.ibatis.type.Alias;
/**
* system_role 表模型
* @author King
*/
@Alias("systemRole")
@TableName(value = "system_role")
public class SystemRoleDO {

        /**
        * 列名: ROLE_ID
        * 备注: 主键id
        */
        @TableId
        @TableField("ROLE_ID")
        private String roleId;

        /**
        * 列名: ROLE_NAME
        * 备注: 角色名称
        */
        @TableField("ROLE_NAME")
        private String roleName;

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


        public String getRoleId(){
        return roleId;
      }

        public void setRoleId(String roleId){
          this.roleId = roleId;
      }

        public String getRoleName(){
        return roleName;
      }

        public void setRoleName(String roleName){
          this.roleName = roleName;
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