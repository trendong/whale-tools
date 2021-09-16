package com.whale.service;

import com.whale.common.CommonProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonService {

    private CommonProperties commonProperties;
    @Autowired
    public void CommonService(CommonProperties commonProperties) {
        this.commonProperties = commonProperties;
    }

    public String sayHello() {
        return commonProperties.getUsername() ;
    }

}
