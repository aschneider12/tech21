package br.com.fiap.restaurante.core.domain.usecases.restaurante;

import br.com.fiap.restaurante.core.domain.entities.Restaurante;
import br.com.fiap.restaurante.core.dtos.restaurante.RestauranteInputDTO;
import br.com.fiap.restaurante.core.exceptions.EntidadeJaExisteException;
import br.com.fiap.restaurante.core.interfaces.gateway.IRestauranteGateway;

/**
 * Realilza o cadastro de um novo restaurante.
 */
public class UseCaseCadastrarRestaurante {

    private final IRestauranteGateway gateway;

    /* Metodo internalizado, forçar o uso do create */
    private UseCaseCadastrarRestaurante(IRestauranteGateway gateway) {
        this.gateway = gateway;
    }
    /* poderia ser utilizado direto o construtor forçando o param gateway */
    public static UseCaseCadastrarRestaurante create(IRestauranteGateway gateway) {
        return new UseCaseCadastrarRestaurante(gateway);
    }

    public Restaurante run(RestauranteInputDTO dto) {

        final Restaurante restauranteExistente = gateway.buscarRestaurantePorNome(dto.nome());

        if (restauranteExistente != null)
            throw new EntidadeJaExisteException("Restaurante", dto.nome());


        final Restaurante novoRestaurante = Restaurante.create(
                dto.nome(), dto.tipoCozinha(), dto.horarioFuncionamento()
        );

        Restaurante cadastrado = gateway.cadastrar(novoRestaurante);

        return cadastrado;

    }
}
