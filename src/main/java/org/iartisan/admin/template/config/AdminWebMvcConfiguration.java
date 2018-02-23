package org.iartisan.admin.template.config;

import org.iartisan.runtime.env.EnvContextConfig;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * @author King
 * @since 2018/2/23
 */
@Configuration
public class AdminWebMvcConfiguration implements ServletContextInitializer {


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.setAttribute("_title", EnvContextConfig.get("iartisan.admin.title"));
    }
}
