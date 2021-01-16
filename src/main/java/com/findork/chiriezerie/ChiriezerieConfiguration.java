package com.findork.chiriezerie;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.HashSet;

@Configuration
@EnableSwagger2
public class ChiriezerieConfiguration {

    @Bean
    public Docket defaultApi() {

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.findork.chiriezerie"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .pathMapping("/")
                .protocols(new HashSet<>(Collections.singletonList("https")))
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Chiriezerie")
                .description("Blanao")
                .build();
    }
}