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
                .addPathPatterns("/api/**") //token验证接口
                .excludePathPatterns("/api/user/login","/api/user/register","/api/getCode","/api/forgetPassword",  //放行接口
                        "/swagger-ui.html/**", "/v2/**","/swagger-resources/**","/webjars/**","/*.html"); //swagger相关的接口
    }
}
