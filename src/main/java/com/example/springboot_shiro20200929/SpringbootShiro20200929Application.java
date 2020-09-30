package com.example.springboot_shiro20200929;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

@MapperScan("com.example.springboot_shiro20200929.mapper")
public class SpringbootShiro20200929Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootShiro20200929Application.class, args);
    }

}
