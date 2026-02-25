package org.accesodatos.hogwarts.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI hogwartsOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Hogwarts API")
                        .description("API REST para la gestión del mundo mágico de Hogwarts: " +
                                "estudiantes, profesores, casas, asignaturas y mascotas.")
                        .version("1.0.0"));
    }
}
