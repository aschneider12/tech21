package br.com.fiap.restaurante.application.usecases.restaurante;

import br.com.fiap.restaurante.application.input.RestauranteInput;
import br.com.fiap.restaurante.application.output.RestauranteOutput;
import br.com.fiap.restaurante.domain.interfaces.gateway.IRestauranteGateway;
import br.com.fiap.restaurante.domain.models.Restaurante;
import br.com.fiap.restaurante.application.exceptions.EntidadeJaExisteException;
import br.com.fiap.restaurante.infra.dtos.RestauranteDTO;

public class UseCaseCadastrarRestaurante {

    private final IRestauranteGateway gateway;

    private UseCaseCadastrarRestaurante(IRestauranteGateway gateway) {
        this.gateway = gateway;
    }

    public static UseCaseCadastrarRestaurante create(IRestauranteGateway gateway) {
        return new UseCaseCadastrarRestaurante(gateway);
    }

    public RestauranteOutput run(RestauranteInput input) throws EntidadeJaExisteException {

        //a partir daqui, trabalharia com dominio, sem mapper ou bibliotecas externas

        Restaurante restaurante =  RestauranteInput.toDomain(input);

        Restaurante restauranteExistente = gateway.buscarRestaurantePorNome(restaurante.getNome());
        if (restauranteExistente != null)
            throw new EntidadeJaExisteException("Restaurante", restaurante.getNome());

        Restaurante cadastrado = gateway.cadastrar(restaurante);

        return RestauranteOutput.fromDomain(cadastrado);
    }

}
