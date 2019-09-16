package com.indusfo.spc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@MapperScan(basePackages = "com.indusfo.spc.mapper")
@CrossOrigin
public class Jpa05Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Jpa05Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Jpa05Application.class, args);
    }

}
