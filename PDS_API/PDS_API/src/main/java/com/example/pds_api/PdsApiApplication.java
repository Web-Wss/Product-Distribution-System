package com.example.pds_api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@MapperScan("com.example.pds_api.mapper")
public class PdsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PdsApiApplication.class, args);
    }

}
