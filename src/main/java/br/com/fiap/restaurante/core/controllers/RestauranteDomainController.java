package br.com.fiap.restaurante.core.controllers;

import br.com.fiap.restaurante.core.domain.entities.Restaurante;
import br.com.fiap.restaurante.core.domain.usecases.restaurante.FactoryRestauranteUseCase;
import br.com.fiap.restaurante.core.domain.usecases.restaurante.UseCaseBuscarRestaurantePorID;
import br.com.fiap.restaurante.core.domain.usecases.restaurante.UseCaseBuscarTodosRestaurantes;
import br.com.fiap.restaurante.core.domain.usecases.restaurante.UseCaseCadastrarRestaurante;
import br.com.fiap.restaurante.core.dtos.restaurante.RestauranteCadastroDTO;
import br.com.fiap.restaurante.core.dtos.restaurante.RestauranteRetornoDTO;
import br.com.fiap.restaurante.core.exceptions.EntidadeJaExisteException;
import br.com.fiap.restaurante.core.gateways.RestauranteGateway;
import br.com.fiap.restaurante.core.interfaces.IDataStorageRestaurante;
import br.com.fiap.restaurante.core.presenters.RestaurantePresenter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * DOMAIN CONTROLLER
 *
 * Porta de entrada para a application, todos devem instanciar um domain controller
 *
 * - Não confundir com o RestController
 * - Não teremos mais a camada service, já que está será executada pelos nossos use cases
 *
 */
public class RestauranteDomainController {

    private final IDataStorageRestaurante dataSource;
    private final RestauranteGateway gateway;
    FactoryRestauranteUseCase factoryRestauranteUseCase;
    private RestauranteDomainController(IDataStorageRestaurante iDataStorageRestaurante){
        this.dataSource = iDataStorageRestaurante;
        this.gateway = RestauranteGateway.create(dataSource);
        factoryRestauranteUseCase = new FactoryRestauranteUseCase(gateway);
    }

    public static RestauranteDomainController create(IDataStorageRestaurante iDataStorageRestaurante) {
        return new RestauranteDomainController(iDataStorageRestaurante);
    }

    public RestauranteRetornoDTO cadastrar(RestauranteCadastroDTO dto) {

        var useCaseCadastrarRestaurante = UseCaseCadastrarRestaurante.create(gateway);

        try {

            var estudante = useCaseCadastrarRestaurante.run(dto);
            var retornoDTO = RestaurantePresenter.ToDTO(estudante);
            return retornoDTO;

        } catch (EntidadeJaExisteException e) {
            System.err.println(e.getMessage());
            return null;  // deve retornar algo melhor
        }
    }

    public List<RestauranteRetornoDTO> buscarTodosRestaurantes() {

        var useCaseBuscarTodosRestaurantes = UseCaseBuscarTodosRestaurantes.create(gateway);

        List<Restaurante> run = useCaseBuscarTodosRestaurantes.run();

        return run.stream().map(s
                -> new RestauranteRetornoDTO(s.getId(), s.getNome(), s.getTipoCozinha(), s.getHorarioFuncionamento()))
                .collect(Collectors.toList());
    }

    public RestauranteRetornoDTO buscarPorId(Long id) {
        UseCaseBuscarRestaurantePorID.create(gateway);
        FactoryRestauranteUseCase.

    }

    public boolean deletar(Long id) {
    }

    public RestauranteRetornoDTO atualizar(RestauranteCadastroDTO dto, Long id) {
    }
}
