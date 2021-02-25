package com.example.demo;

import com.example.demo.interceptors.base.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class DemoApplication implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TracingInterceptor()).order(1);
        registry.addInterceptor(new VisitingLoggerInterceptor()).order(2);
        registry.addInterceptor(new TimingForInterceptor()).order(3);

        registry.addInterceptor(new TimingForTest1()).order(10);
        registry.addInterceptor(new TimingForTest2()).order(11);


        registry.addInterceptor(new TimingForController()).order(Integer.MAX_VALUE);
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    @GetMapping("/helloWorld")
    public String helloWorld() {
        return "hello world";
    }
}
