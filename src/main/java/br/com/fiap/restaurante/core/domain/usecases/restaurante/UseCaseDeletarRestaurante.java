package br.com.fiap.restaurante.core.domain.usecases.restaurante;

import br.com.fiap.restaurante.core.domain.entities.Restaurante;
import br.com.fiap.restaurante.core.dtos.restaurante.RestauranteInputDTO;
import br.com.fiap.restaurante.core.exceptions.EntidadeJaExisteException;
import br.com.fiap.restaurante.core.exceptions.EntidadeNaoEncontradaException;
import br.com.fiap.restaurante.core.interfaces.gateway.IRestauranteGateway;

public class UseCaseDeletarRestaurante {

    private final IRestauranteGateway gateway;

    private UseCaseDeletarRestaurante(IRestauranteGateway gateway) {
        this.gateway = gateway;
    }
    public static UseCaseDeletarRestaurante create(IRestauranteGateway gateway) {
        return new UseCaseDeletarRestaurante(gateway);
    }

    public boolean run(Long id) throws EntidadeJaExisteException {
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
