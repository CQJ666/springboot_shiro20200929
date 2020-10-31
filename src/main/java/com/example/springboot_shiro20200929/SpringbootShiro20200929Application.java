package com.example.springboot_shiro20200929;

import org.junit.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication

@MapperScan("com.example.springboot_shiro20200929.mapper")
public class SpringbootShiro20200929Application {
    private static Logger logger= LoggerFactory.getLogger(SpringbootShiro20200929Application.class);
    public static void main(String[] args) {
        SpringApplication.run(SpringbootShiro20200929Application.class, args);

        logger.info("shiro20200929启动完毕-------------------------------");

    }



    @Test
    public void aa(){
        String  aa=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
//        Integer now = Integer.valueOf();

        System.out.println(System.currentTimeMillis());
        System.out.println(Long.valueOf(aa));
//        System.out.println(now);
    }
}
