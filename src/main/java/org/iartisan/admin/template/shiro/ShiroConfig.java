package org.iartisan.admin.template.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.iartisan.admin.template.authentication.AuthenticationService;
import org.iartisan.admin.template.contants.ReqContants;
import org.iartisan.runtime.env.EnvContextConfig;
import org.iartisan.runtime.utils.StringUtils;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Configuration
public class ShiroConfig {

    public ShiroConfig() {
        setFilterMap();
    }

    private Map<String, String> filterMap = new LinkedHashMap<>();

    private static final String _CONFIG = "iartisan.shiro.filter";

    private static final String anon = "anon";
    private static final String authc = "authc";

    public Map<String, String> getFilterMap() {
        return filterMap;
    }

    public void setFilterMap() {
        String filterPath = EnvContextConfig.get(_CONFIG, "");
        filterPath = filterPath + ",/assets/iartisan/**," + ReqContants.REQ_AUTHENTICATE +
                "," + ReqContants.REQ_LOGIN + ",/webjars/**";
        List<String> filterPaths = Arrays.asList(filterPath.split(","));
        for (String path : filterPaths) {
            if (StringUtils.isNotEmpty(path)){
                filterMap.put(path, anon);
            }
        }
        filterMap.put("/**", authc);
    }

    @Bean("sessionManager")
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionIdUrlRewritingEnabled(true);
        return sessionManager;
    }

    @Bean("securityManager")
    public SecurityManager securityManager(AuthenticationService authenticationService, SessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(authenticationService);
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        shiroFilter.setLoginUrl(ReqContants.REQ_LOGIN);
        shiroFilter.setUnauthorizedUrl("/");
        shiroFilter.setFilterChainDefinitionMap(getFilterMap());
        return shiroFilter;
    }

    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    //@Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }

    // @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

}
