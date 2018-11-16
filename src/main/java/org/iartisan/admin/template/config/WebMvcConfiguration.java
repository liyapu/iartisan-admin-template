package org.iartisan.admin.template.config;

import org.iartisan.runtime.env.EnvContextConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * @author King
 * @since 2018/2/23
 */
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter implements ServletContextInitializer {

    @Autowired
    private EnvironmentInterceptor environmentInterceptor;

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.setAttribute("_title", EnvContextConfig.get("iartisan.admin.title"));
        servletContext.setAttribute("_authorEmail", EnvContextConfig.get("iartisan.author.email"));
        servletContext.setAttribute("staticVerison", EnvContextConfig.get("iartisan.static.version", "00000"));
    }

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {

        return (container -> {
            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404");
            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500");

            container.addErrorPages(error404Page, error500Page);
        });
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(environmentInterceptor).addPathPatterns("/**/**/deleteData", "/**/**/modifyData", "/userSupport/changeStatus", "/userSupport/modifyPwd");
    }
}
