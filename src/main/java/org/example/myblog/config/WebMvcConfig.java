package org.example.myblog.config;

import org.example.myblog.interceptor.DefaultInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    // interceptor 설정
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new DefaultInterceptor())
                // interceptor가 작동해야 하는 url 패턴 설정
                .addPathPatterns("/api/**")
                // interceptor가 작동하지 않아야 하는 url 패턴 설정
                .excludePathPatterns("/resource/**", "/api/auth", "/api/user/signup", "/api/user/login");
    }
}
