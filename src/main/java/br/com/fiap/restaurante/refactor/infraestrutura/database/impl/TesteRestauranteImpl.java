package br.com.fiap.restaurante.refactor.infraestrutura.database.impl;

import br.com.fiap.restaurante.refactor.core.gateway.TesteRestaurante;
import br.com.fiap.restaurante.refactor.domain.model.RestauranteDomain;
import br.com.fiap.restaurante.refactor.infraestrutura.database.entities.TesteRestauranteEntity;
import br.com.fiap.restaurante.refactor.infraestrutura.database.mapper.RestauranteEntityMapper;
import br.com.fiap.restaurante.refactor.infraestrutura.database.repository.TesteRestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TesteRestauranteImpl implements TesteRestaurante {

    private final TesteRestauranteRepository repository;

    @Override
    public void inserir(RestauranteDomain domain) {
        TesteRestauranteEntity entity = RestauranteEntityMapper.INSTANCE.toTesteRestauranteEntity(domain);
        repository.save(entity);
    }
}
