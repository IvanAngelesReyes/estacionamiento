package com.estacionamiento.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("🚗 API Estacionamiento")  
                .version("1.0")
                .description("Documentación de las APIs para el sistema de estacionamiento")
                .contact(new Contact()
                    .name("Soporte Técnico")
                    .email("soporte@estacionamiento.com")
                    .url("https://estacionamiento.com/contacto"))
                .license(new License()
                    .name("Apache 2.0")
                    .url("https://www.apache.org/licenses/LICENSE-2.0")));
    }
	
	// @Bean
    // public GroupedOpenApi logInGroup(){
    //     return GroupedOpenApi.builder()
    //                 .group("LogIn")
    //                 .pathsToMatch("/api/auth/**"
    //                                             )
    //                               .build();
    // }

    // @Bean
    // public GroupedOpenApi usersGroup(){
    //     return GroupedOpenApi.builder()
    //                 .group("Usuarios")
    //                 .pathsToMatch("/api/users/**",
    //                                             "/api/roles/**",
    //                                             "/api/permissions/**"
    //                                             )
    //                               .build();
    // }
}