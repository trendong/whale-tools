package com.whale.common;

import com.whale.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CommonExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonExampleApplication.class, args);
    }

    private CommonService commonService;
    @Autowired
    public void setCommonService(CommonService commonService) {
        this.commonService = commonService;
    }

    @GetMapping("/sayHello")
    public String sayHello() {
        return commonService.sayHello();
    }


}
