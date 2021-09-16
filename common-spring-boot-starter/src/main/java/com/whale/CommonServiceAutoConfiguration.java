package com.whale;

import com.whale.common.CommonProperties;
import com.whale.service.CommonService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(CommonService.class)
@ConditionalOnProperty(prefix = "common.service",value = "enable",matchIfMissing = true)
@EnableConfigurationProperties(CommonProperties.class)
public class CommonServiceAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public CommonService greeter() {
        return new CommonService();
    }

}
