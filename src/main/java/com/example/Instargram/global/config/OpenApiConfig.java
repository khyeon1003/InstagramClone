package com.example.Instargram.global.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
    info=@Info(
            title = "Instagram API 명세서",
            description = "API 명세서",
            version = "v1",
            contact = @Contact(
                    name = "강용현",
                    email = "khyeon001003@gmail.com"
            )
    )
)


@Configuration
public class OpenApiConfig {
}
