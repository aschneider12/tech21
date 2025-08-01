package br.com.fiap.restaurante.infra.config;

import br.com.fiap.restaurante.application.gateways.RestauranteGateway;
import br.com.fiap.restaurante.application.usecases.restaurante.FactoryRestauranteUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestauranteConfig {

    @Bean
    public FactoryRestauranteUseCase createFactoryRestaurante(RestauranteGateway gateway) {
        return new FactoryRestauranteUseCase(gateway);
    }
}
