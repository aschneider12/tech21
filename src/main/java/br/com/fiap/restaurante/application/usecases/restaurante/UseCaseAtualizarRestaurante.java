package br.com.fiap.restaurante.application.usecases.restaurante;

import br.com.fiap.restaurante.application.exceptions.EntidadeNaoEncontradaException;
import br.com.fiap.restaurante.application.input.RestauranteInput;
import br.com.fiap.restaurante.application.output.RestauranteOutput;
import br.com.fiap.restaurante.domain.interfaces.gateway.IRestauranteGateway;
import br.com.fiap.restaurante.domain.models.Restaurante;

public class UseCaseAtualizarRestaurante {

    private final IRestauranteGateway gateway;

    private UseCaseAtualizarRestaurante(IRestauranteGateway gateway) {
        this.gateway = gateway;
    }

    public static UseCaseAtualizarRestaurante create(IRestauranteGateway gateway) {
        return new UseCaseAtualizarRestaurante(gateway);
    }

    public RestauranteOutput run(RestauranteInput input, Long id)
            throws EntidadeNaoEncontradaException {

        Restaurante restaurante = gateway.buscarRestaurantePorIdentificador(id);

        if(restaurante == null)
            throw new EntidadeNaoEncontradaException("Restaurante", "ID - "+id);

        restaurante = RestauranteInput.toDomain(input);

        //aqui, poderia ser enviada a entidade recuperada e só trocar os campos?
        //isso evitaria que a entidade ja possuisse campos e eles fossem desatualizados?

//        Restaurante restauranteAtualizar = RestauranteMapper.toDomain(dto);
//        restauranteAtualizar.setId(id);

        Restaurante atualizado = gateway.atualizar(restaurante);

        return RestauranteOutput.fromDomain(atualizado);
    }
}
