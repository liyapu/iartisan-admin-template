package org.iartisan.admin.template.authentication;

/**
 * <p>
 * 登录用户
 *
 * @author King
 * @since 2017/7/4
 */
public class RealmBean {

    private String userId;

    private String userName;

    private String userPwd;//用户密码

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
