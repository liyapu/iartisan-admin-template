package org.iartisan.admin.template;


import org.iartisan.runtime.env.EnvPropertiesLoader;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.util.Properties;

/**
 * <p>
 * 启动类
 *
 * @author King
 * @since 2017/10/19
 */
@SpringBootApplication
@MapperScan(basePackages = {"org.iartisan.admin.template.dao.mapper"})
public class WebBootstrap {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(WebBootstrap.class);
        Properties properties = EnvPropertiesLoader.loadFile();
        application.setDefaultProperties(properties);
        application.run(args);
    }

}
