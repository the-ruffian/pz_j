package com.example.demo.config;

import com.example.demo.insterceptors.JWTInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JWTInterceptor())
                .addPathPatterns("/**") //token验证接口
                .excludePathPatterns("/api/user/login","/api/user/register",  //放行接口
                        "/swagger-ui.html/**", "/v2/**","/swagger-resources/**","/webjars/**"); //swagger相关的接口
    }
}
