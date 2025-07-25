package br.com.fiap.restaurante.core.usecases.restaurante;

import br.com.fiap.restaurante.core.domain.interfaces.gateway.IRestauranteGateway;
import br.com.fiap.restaurante.core.domain.models.Restaurante;
import br.com.fiap.restaurante.domain.domain.models.Restaurante;
import br.com.fiap.restaurante.application.dtos.restaurante.RestauranteInputDTO;
import br.com.fiap.restaurante.application.dtos.restaurante.RestauranteOutputDTO;
import br.com.fiap.restaurante.domain.exceptions.EntidadeNaoEncontradaException;
import br.com.fiap.restaurante.domain.domain.interfaces.gateway.IRestauranteGateway;
import br.com.fiap.restaurante.infra.controller.mappers.RestauranteMapper;

public class UseCaseAtualizarRestaurante {

    private final IRestauranteGateway gateway;

    private UseCaseAtualizarRestaurante(IRestauranteGateway gateway) {
        this.gateway = gateway;
    }

    public static UseCaseAtualizarRestaurante create(IRestauranteGateway gateway) {
        return new UseCaseAtualizarRestaurante(gateway);
    }

    public Restaurante run(Restaurante restaurante, Long id)
            throws EntidadeNaoEncontradaException {

        Restaurante restaurante = gateway.buscarRestaurantePorIdentificador(id);

        if(restaurante == null)
            throw new EntidadeNaoEncontradaException("Restaurante", "ID - "+id);

        //aqui, poderia ser enviada a entidade recuperada e só trocar os campos?
        //isso evitaria que a entidade ja possuisse campos e eles fossem desatualizados?

        Restaurante restauranteAtualizar = RestauranteMapper.toDomain(dto);
        restauranteAtualizar.setId(id);

        Restaurante atualizado = gateway.atualizar(restauranteAtualizar);

        return RestauranteMapper.toOutput(atualizado);
    }
}
