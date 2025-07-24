package br.com.fiap.restaurante.core.controllers;

import br.com.fiap.restaurante.core.domain.usecases.restaurante.FactoryRestauranteUseCase;
import br.com.fiap.restaurante.core.domain.usecases.restaurante.UseCaseCadastrarRestaurante;
import br.com.fiap.restaurante.core.domain.usecases.restaurante.UseCaseDeletarRestaurante;
import br.com.fiap.restaurante.core.dtos.restaurante.RestauranteInputDTO;
import br.com.fiap.restaurante.core.dtos.restaurante.RestauranteOutputDTO;
import br.com.fiap.restaurante.core.exceptions.EntidadeJaExisteException;
import br.com.fiap.restaurante.core.gateways.RestauranteGateway;
import br.com.fiap.restaurante.core.interfaces.storage.IDataStorageRestaurante;

import java.util.List;

/**
 * DOMAIN CONTROLLER
 * Porta de entrada para a application, todos devem instanciar um domain controller
  * - Não confundir com o RestController
 * - Não teremos mais a camada service, já que está será executada pelos nossos use cases
 *
 */
public class RestauranteDomainController {

    private final FactoryRestauranteUseCase factoryRestauranteUseCase;

    private RestauranteDomainController(IDataStorageRestaurante iDataStorageRestaurante){
        RestauranteGateway gateway = RestauranteGateway.create(iDataStorageRestaurante);
        factoryRestauranteUseCase = new FactoryRestauranteUseCase(gateway);
    }

    public static RestauranteDomainController create(IDataStorageRestaurante iDataStorageRestaurante) {
        return new RestauranteDomainController(iDataStorageRestaurante);
    }

    public RestauranteOutputDTO cadastrar(RestauranteInputDTO dto) {

        UseCaseCadastrarRestaurante useCase = factoryRestauranteUseCase.cadastrarRestaurante();

        try {

            var restaurante = useCase.run(dto);

            // var retornoDTO = RestaurantePresenter.ToDTO(restaurante);
            return restaurante;

        } catch (EntidadeJaExisteException e) {
            System.err.println(e.getMessage());
            return null;  // deve retornar algo melhor
        }
    }

    public List<RestauranteOutputDTO> buscarTodosRestaurantes() {

        //o controller recebe da implementação interna o Restaurante concreto
        //como vai devolver para o externo (rest no nosso caso) ele traduz para DTO novamente

        var useCase = factoryRestauranteUseCase.buscarTodosRestaurantes();

        List<RestauranteOutputDTO> allRestaurants = useCase.run();

        return  allRestaurants;
    }

    public RestauranteOutputDTO buscarPorId(Long id) {

        var useCase = factoryRestauranteUseCase.buscarRestaurantePorId();

       return useCase.run(id);
    }

    public boolean deletar(Long id) {

        UseCaseDeletarRestaurante useCase = factoryRestauranteUseCase.deletarRestaurante();

        return useCase.run(id);
    }

    public RestauranteOutputDTO atualizar(RestauranteInputDTO dto, Long id) {

        var useCase = factoryRestauranteUseCase.atualizarRestaurante();

        return useCase.run(dto, id);
    }
}
