package com.poype.heracles;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@MapperScan("com.poype.heracles.core.repository.dao")
@ImportResource("classpath:/spring/aop.xml")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
