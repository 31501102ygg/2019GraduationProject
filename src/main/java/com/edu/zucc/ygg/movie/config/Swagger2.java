package com.edu.zucc.ygg.movie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.edu.zucc.ygg.movie.controller"))
                .paths(PathSelectors.any())//设置扫描的包路径
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("基于Spring Boot的电影影评网站")
                .description("对外API接口") //设置描述之类的东西
//                .termsOfServiceUrl("https://www.jianshu.com/u/7fcaa9fcd2c1")
                .contact(new Contact("31501102_叶港归", "", "409803540@qq.com"))
                .version("1.0")
                .build();
    }
}
