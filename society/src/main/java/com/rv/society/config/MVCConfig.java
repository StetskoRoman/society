package com.rv.society.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//Загружает сразу ресурсы

@Configuration
public class MVCConfig implements WebMvcConfigurer {

    @Value("{upload.path}")
    private String uploadPath;

//    https://spring.io/guides/gs/consuming-rest/
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }


    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/login").setViewName("login");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file://" + uploadPath + "/");
//        "/static/**" при обращении по этому пути ресурсы будут искаться в дереве проекта
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
}
