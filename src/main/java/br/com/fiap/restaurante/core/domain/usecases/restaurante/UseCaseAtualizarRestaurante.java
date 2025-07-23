package br.com.fiap.restaurante.core.domain.usecases.restaurante;

import br.com.fiap.restaurante.core.domain.entities.Restaurante;
import br.com.fiap.restaurante.core.dtos.restaurante.RestauranteInputDTO;
import br.com.fiap.restaurante.core.dtos.restaurante.RestauranteOutputDTO;
import br.com.fiap.restaurante.core.exceptions.EntidadeJaExisteException;
import br.com.fiap.restaurante.core.exceptions.EntidadeNaoEncontradaException;
import br.com.fiap.restaurante.core.interfaces.gateway.IRestauranteGateway;

public class UseCaseAtualizarRestaurante {

    private final IRestauranteGateway gateway;

    /* Metodo internalizado, forçar o uso do create */
    private UseCaseAtualizarRestaurante(IRestauranteGateway gateway) {
        this.gateway = gateway;
    }
    /* poderia ser utilizado direto o construtor forçando o param gateway */
    public static UseCaseAtualizarRestaurante create(IRestauranteGateway gateway) {
        return new UseCaseAtualizarRestaurante(gateway);
    }

    public RestauranteOutputDTO run(RestauranteInputDTO dto, Long id) throws EntidadeJaExisteException {
//terminar o cadastrar
        try {

            Restaurante restauranteExistente = new UseCaseBuscarRestaurantePorNome(gateway).run(dto.nome());
            if (restauranteExistente != null)
                throw new EntidadeJaExisteException("Restaurante", dto.nome());

        } catch (EntidadeNaoEncontradaException ex) {

        }

        final Restaurante novoRestaurante = Restaurante.create(
                dto.nome(), dto.tipoCozinha(), dto.horarioFuncionamento()
        );

        Restaurante cadastrado = gateway.cadastrar(novoRestaurante);
        final Restaurante restauranteExistente2 = gateway.buscarRestaurantePorNome(dto.nome());






        return cadastrado;

    }
}
