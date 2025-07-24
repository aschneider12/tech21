package br.com.fiap.restaurante.core.domain.usecases.restaurante;

import br.com.fiap.restaurante.core.domain.entities.Restaurante;
import br.com.fiap.restaurante.core.dtos.restaurante.RestauranteInputDTO;
import br.com.fiap.restaurante.core.dtos.restaurante.RestauranteOutputDTO;
import br.com.fiap.restaurante.core.exceptions.EntidadeJaExisteException;
import br.com.fiap.restaurante.core.interfaces.gateway.IRestauranteGateway;
import br.com.fiap.restaurante.core.mappers.RestauranteMapper;

public class UseCaseCadastrarRestaurante {

    private final IRestauranteGateway gateway;

    private UseCaseCadastrarRestaurante(IRestauranteGateway gateway) {
        this.gateway = gateway;
    }

    public static UseCaseCadastrarRestaurante create(IRestauranteGateway gateway) {
        return new UseCaseCadastrarRestaurante(gateway);
    }

    public RestauranteOutputDTO run(RestauranteInputDTO dto) throws EntidadeJaExisteException {

        Restaurante restauranteCadastrar  = gateway.buscarRestaurantePorNome(dto.nome());
        if (restauranteCadastrar != null)
            throw new EntidadeJaExisteException("Restaurante", dto.nome());

        restauranteCadastrar = RestauranteMapper.toDomain(dto);

        Restaurante cadastrado = gateway.cadastrar(restauranteCadastrar);

        return RestauranteMapper.toOutput(cadastrado);
    }

}
