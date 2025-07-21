package br.com.fiap.restaurante.application.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * http://localhost:8080/swagger-ui/index.html
 *
 * Ainda faltam validaçoes nos campos da documentação.
 *
 * @author aschneider12
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                .title("Grupo 83 - API TechChallenge")
                .version("1.0")
                .description("Documentação da API usando Swagger com Spring Boot 3.4.4"));
    }
}
