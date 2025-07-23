package br.com.fiap.restaurante.core.controllers;

import br.com.fiap.restaurante.core.domain.entities.Restaurante;
import br.com.fiap.restaurante.core.domain.usecases.restaurante.FactoryRestauranteUseCase;
import br.com.fiap.restaurante.core.domain.usecases.restaurante.UseCaseCadastrarRestaurante;
import br.com.fiap.restaurante.core.dtos.restaurante.RestauranteInputDTO;
import br.com.fiap.restaurante.core.dtos.restaurante.RestauranteOutputDTO;
import br.com.fiap.restaurante.core.exceptions.EntidadeJaExisteException;
import br.com.fiap.restaurante.core.gateways.RestauranteGateway;
import br.com.fiap.restaurante.core.interfaces.storage.IDataStorageRestaurante;
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

    private final RestauranteGateway gateway;
    private final FactoryRestauranteUseCase factoryRestauranteUseCase;

    private RestauranteDomainController(IDataStorageRestaurante iDataStorageRestaurante){
        this.gateway = RestauranteGateway.create(iDataStorageRestaurante);
        factoryRestauranteUseCase = new FactoryRestauranteUseCase(gateway);
    }

    public static RestauranteDomainController create(IDataStorageRestaurante iDataStorageRestaurante) {
        return new RestauranteDomainController(iDataStorageRestaurante);
    }

    public RestauranteOutputDTO cadastrar(RestauranteInputDTO dto) {

        var useCaseCadastrarRestaurante = UseCaseCadastrarRestaurante.create(gateway);

        try {

            var restaurante = useCaseCadastrarRestaurante.run(dto);
            var retornoDTO = RestaurantePresenter.ToDTO(restaurante);
            return retornoDTO;

        } catch (EntidadeJaExisteException e) {
            System.err.println(e.getMessage());
            return null;  // deve retornar algo melhor
        }
    }

    public List<RestauranteOutputDTO> buscarTodosRestaurantes() {

        //o controller recebe da implementação interna o Restaurante concreto
        //como vai devolver para o externo (rest no nosso caso) ele traduz para DTO novamente

        var useCase = factoryRestauranteUseCase.buscarTodosRestaurantes();

        List<Restaurante> allRestaurants = useCase.run();

        return allRestaurants.stream().map(s
                -> new RestauranteOutputDTO(s.getId(), s.getNome(), s.getTipoCozinha(), s.getHorarioFuncionamento()))
                .collect(Collectors.toList());
    }

    public RestauranteOutputDTO buscarPorId(Long id) {

        var useCase = factoryRestauranteUseCase.buscarRestaurantePorId();

        var restaurante = useCase.run(id);

        return new RestauranteOutputDTO(restaurante.getId(), restaurante.getNome(), restaurante.getTipoCozinha(), restaurante.getHorarioFuncionamento());
    }

    public boolean deletar(Long id) {

        throw new RuntimeException("DELETAR - Não foi implementado ainda!");
//        var useCase = factoryRestauranteUseCase.deletarRestaurante();
//        return useCase.run();
    }

    public RestauranteOutputDTO atualizar(RestauranteInputDTO dto, Long id) {

        throw new RuntimeException("ATUALIZAR - Não foi implementado ainda!");
//        return null;
    }
}
