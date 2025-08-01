package br.com.fiap.restaurante.infra.config;

import br.com.fiap.restaurante.application.gateways.ItemCardapioGateway;
import br.com.fiap.restaurante.application.usecases.itemcardapio.FactoryCardapioUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemCardapioConfig {

    @Bean
    public FactoryCardapioUseCase createFactoryCardapio(ItemCardapioGateway gateway) {
        return new FactoryCardapioUseCase(gateway);
    }
}
