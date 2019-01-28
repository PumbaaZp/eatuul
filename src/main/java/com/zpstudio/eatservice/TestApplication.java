package com.zpstudio.eatservice;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @Description TODO
 * @Author zhangpeng
 * @Date 2019/1/28 14:33
 **/

@SpringBootApplication
@ServletComponentScan(basePackageClasses = IndexController.class)
public class TestApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(TestApplication.class).properties("server.port=9090").run(args);
    }
}
