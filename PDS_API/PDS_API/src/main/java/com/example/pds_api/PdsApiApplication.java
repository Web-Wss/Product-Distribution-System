package com.example.pds_api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.example.pds_api.mapper")
public class PdsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PdsApiApplication.class, args);
    }

}
