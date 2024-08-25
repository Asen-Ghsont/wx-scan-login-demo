package com.example.wxscanlogindemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class WxScanLoginDemoApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(WxScanLoginDemoApplication.class, args);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 添加映射路径
        registry.addMapping("/**")
                .allowedOriginPatterns("*") // 允许哪些域的请求，星号代表允许所有
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE") // 允许的方法
                .allowedHeaders("*") // 允许的头部设置
                .allowCredentials(true) // 是否发送cookie
                .maxAge(168000); // 预检间隔时间
    }
}
