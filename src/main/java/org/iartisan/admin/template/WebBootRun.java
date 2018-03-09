package org.iartisan.admin.template;

import org.iartisan.runtime.env.EnvPropertiesLoader;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Properties;

/**
 * <p>
 * 启动类
 *
 * @author King
 * @since 2017/10/19
 */
@SpringBootApplication
@ComponentScan(basePackages = {"org.iartisan.admin.template","org.iartisan.runtime.web.config"})
@MapperScan(basePackages = {"org.iartisan.admin.template.authentication.support.dbm.mapper"})
public class WebBootRun {

    public static void main(String[] args) {
        Properties properties = EnvPropertiesLoader.loadFile();
        SpringApplication application = new SpringApplication(WebBootRun.class);
        application.setDefaultProperties(properties);
        application.run(args);
       // new SpringApplicationBuilder(WebBootRun.class).web(true).run(args);
    }

}
