package com.project.order.server.config;

import com.project.core.utils.IdWorkerUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动注入第三方jar的bean
 */
@Configuration
public class AutoConfigrution {
    @Bean
    public IdWorkerUtil constructIdWorkerUtil() {
        IdWorkerUtil idWorkerUtil = new IdWorkerUtil();
        return idWorkerUtil;
    }
}
