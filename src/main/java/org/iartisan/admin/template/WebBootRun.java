package org.iartisan.admin.template;


import org.activiti.rest.editor.model.ModelSaveRestResource;
import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.iartisan.runtime.env.EnvPropertiesLoader;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

/**
 * <p>
 * 启动类
 *
 * @author King
 * @since 2017/10/19
 */
@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class, SecurityAutoConfiguration.class})
@ComponentScan(basePackages = {"org.iartisan.admin.template", "org.iartisan.runtime.web.config", "org.activiti.rest.editor.model", "org.activiti.rest.editor.main"},
        //过滤掉
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = ModelSaveRestResource.class)})
@MapperScan(basePackages = {"org.iartisan.admin.template.dao.mapper"})
public class WebBootRun {

    public static void main(String[] args) {
        Properties properties = EnvPropertiesLoader.loadFile();
        SpringApplication application = new SpringApplication(WebBootRun.class);
        application.setDefaultProperties(properties);
        application.run(args);
        // new SpringApplicationBuilder(WebBootRun.class).web(true).run(args);
    }

}
