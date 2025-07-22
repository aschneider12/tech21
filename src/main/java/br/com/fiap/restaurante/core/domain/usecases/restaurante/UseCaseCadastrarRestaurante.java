package br.com.fiap.restaurante.core.domain.usecases.restaurante;

import br.com.fiap.restaurante.core.domain.entities.Restaurante;
import br.com.fiap.restaurante.core.dtos.restaurante.RestauranteInputDTO;
import br.com.fiap.restaurante.core.exceptions.EntidadeJaExisteException;
import br.com.fiap.restaurante.core.exceptions.EntidadeNaoEncontradaException;
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
