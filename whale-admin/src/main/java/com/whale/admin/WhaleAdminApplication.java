package com.whale.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@MapperScan("com.whale.admin.web.repository.mapper")
@SpringBootApplication
public class WhaleAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhaleAdminApplication.class, args);
    }

}
