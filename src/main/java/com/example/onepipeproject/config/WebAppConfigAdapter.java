package com.example.onepipeproject.config;

import com.dada.medthreat.interceptor.AuditLogInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAppConfigAdapter implements WebMvcConfigurer {




    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuditLogInterceptor());
    }

}
