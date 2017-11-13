package org.iartisan.admin.template.filter;

import org.iartisan.admin.template.sitemesh.WebSiteMeshConfig;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * <p>
 * web 过滤器
 *
 * @author King
 * @since 2017/11/2
 */
//@Configuration
public class WebMvcFilter extends WebMvcConfigurerAdapter {

    @Bean
    public FilterRegistrationBean siteMeshFilter() {
        FilterRegistrationBean fitler = new FilterRegistrationBean();
        WebSiteMeshConfig siteMeshFilter = new WebSiteMeshConfig();
        fitler.setFilter(siteMeshFilter);
        return fitler;
    }

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {

        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404");
                ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500");
                container.addErrorPages(error404Page, error500Page);
            }
        };
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(new AuthenticationInterceptor())
                .addPathPatterns("/*")
                .excludePathPatterns("/login", "/index", "/authenticate");
    }
}
