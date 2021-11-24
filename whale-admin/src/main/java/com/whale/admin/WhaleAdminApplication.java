package com.whale.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动类
 *
 * @author trendong
 */
@EnableAsync
@EnableScheduling
@MapperScan("com.whale.framework.repository.mapper")
@SpringBootApplication
public class WhaleAdminApplication {

    private static Logger logger = LoggerFactory.getLogger(WhaleAdminApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(WhaleAdminApplication.class, args);
        logger.debug("just do it");
    }

}
