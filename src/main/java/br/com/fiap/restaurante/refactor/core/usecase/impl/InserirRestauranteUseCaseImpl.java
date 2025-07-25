package br.com.fiap.restaurante.refactor.core.usecase.impl;

import br.com.fiap.restaurante.refactor.core.gateway.TesteRestaurante;
import br.com.fiap.restaurante.refactor.core.usecase.InserirRestauranteUseCase;
import br.com.fiap.restaurante.refactor.domain.model.RestauranteDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InserirRestauranteUseCaseImpl implements InserirRestauranteUseCase {

    private final TesteRestaurante testeRestaurante;

    @Override
    public void inserir(RestauranteDomain domain) {

        //regras usecase
        testeRestaurante.inserir(domain);
    }
}
