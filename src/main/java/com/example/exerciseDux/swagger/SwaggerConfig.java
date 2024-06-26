package com.example.exerciseDux.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class SwaggerConfig {

    @Bean
    public OpenAPI api(){
        return new OpenAPI().info(new Info().title("DUX SOFTWARE | PRUEBA TÉCNICA ").version("Snapshot 1.0")
                .contact(new Contact().name("Axel Ruiz").url("https://www.linkedin.com/in/axel-valdez-7447b1236/"))
                .description("Esta api te permite hacer las operaraciones CRUD de un equipo de futbol. Tambien tiene una implementacion de la seguridad basada en JWT.La persistencia esta hecha con una base de datos h2.")
        );

    }
}
