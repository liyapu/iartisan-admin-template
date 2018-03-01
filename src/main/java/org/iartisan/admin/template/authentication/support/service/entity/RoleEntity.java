package org.iartisan.admin.template.authentication.support.service.entity;

/**
 * @author King
 * @since 2018/3/1
 */
public class RoleEntity {

    private String roleName;

    private String permissions;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }
}
