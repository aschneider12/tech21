package br.com.fiap.restaurante.application.controllers;

import br.com.fiap.restaurante.core.domain.interfaces.storage.IDataStorageRestaurante;
import br.com.fiap.restaurante.core.domain.models.Restaurante;
import br.com.fiap.restaurante.core.usecases.restaurante.FactoryRestauranteUseCase;
import br.com.fiap.restaurante.core.usecases.restaurante.UseCaseCadastrarRestaurante;
import br.com.fiap.restaurante.core.usecases.restaurante.UseCaseDeletarRestaurante;
import br.com.fiap.restaurante.application.dtos.restaurante.RestauranteInputDTO;
import br.com.fiap.restaurante.application.dtos.restaurante.RestauranteOutputDTO;
import br.com.fiap.restaurante.domain.exceptions.EntidadeJaExisteException;
import br.com.fiap.restaurante.application.gateways.RestauranteGateway;

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

    public Restaurante cadastrar(Restaurante dto) {

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

    public List<Restaurante> buscarTodosRestaurantes() {

        //o controller recebe da implementação interna o Restaurante concreto
        //como vai devolver para o externo (rest no nosso caso) ele traduz para DTO novamente

        var useCase = factoryRestauranteUseCase.buscarTodosRestaurantes();

        List<Restaurante> allRestaurants = useCase.run();

        return  allRestaurants;
    }

    public Restaurante buscarPorId(Long id) {

        var useCase = factoryRestauranteUseCase.buscarRestaurantePorId();

       return useCase.run(id);
    }

    public boolean deletar(Long id) {

        UseCaseDeletarRestaurante useCase = factoryRestauranteUseCase.deletarRestaurante();

        return useCase.run(id);
    }

    public Restaurante atualizar(Restaurante atualizar, Long id) {

        var useCase = factoryRestauranteUseCase.atualizarRestaurante();

        return useCase.run(atualizar, id);
    }
}
