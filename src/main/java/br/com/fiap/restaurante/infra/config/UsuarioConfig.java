package br.com.fiap.restaurante.infra.config;

import br.com.fiap.restaurante.application.gateways.RestauranteGateway;
import br.com.fiap.restaurante.application.gateways.UsuarioGateway;
import br.com.fiap.restaurante.application.usecases.restaurante.FactoryRestauranteUseCase;
import br.com.fiap.restaurante.application.usecases.usuario.FactoryUsuarioUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UsuarioConfig {

    @Bean
    public FactoryUsuarioUseCase createFactoryUsuario(UsuarioGateway gateway, PasswordEncoder passwordEncoder) {
        return new FactoryUsuarioUseCase(gateway, passwordEncoder);
    }
}
