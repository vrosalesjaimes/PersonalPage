package com.vrj.mysite.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class SwaggerConfig {

    @Bean
    public OpenAPI api(){
        return  new OpenAPI().info(new Info().title("Personal Page").version("1.0 SNAPSHOT")
                .contact(new Contact().name("Victor Rosales").email("rosalesjaimes@gmail.com"))
                .license(new License().url("http://dnfjdfns.com"))
                .description("Personal page to share my academic and professional experiences."));
    }
}
