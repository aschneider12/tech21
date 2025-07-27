package br.com.fiap.restaurante.application.gateways;

import br.com.fiap.restaurante.domain.interfaces.gateway.IRestauranteGateway;
import br.com.fiap.restaurante.domain.interfaces.storage.IDataStorageRestaurante;
import br.com.fiap.restaurante.domain.models.Restaurante;

import java.util.List;

public class RestauranteGateway implements IRestauranteGateway {

    private final IDataStorageRestaurante dataSource;

    private RestauranteGateway(IDataStorageRestaurante iDataStorageRestaurante) {
        this.dataSource = iDataStorageRestaurante;
    }

    public static RestauranteGateway create(IDataStorageRestaurante iDataStorageRestaurante) {
        return new RestauranteGateway(iDataStorageRestaurante);
    }

    @Override
    public Restaurante cadastrar(Restaurante restaurante) {

       // var input = RestauranteMapper.toInput(restaurante);

        var cadastrado = this.dataSource.cadastrar(restaurante);

        return cadastrado;
    }

    @Override
    public Restaurante atualizar(Restaurante restaurante) {
      //  var input = RestauranteMapper.toInput(restaurante);

        var atualizado = this.dataSource.atualizar(restaurante);

        return atualizado;
    }

    @Override
    public boolean deletar(Long id) {
        return this.dataSource.deletar(id);
    }

    @Override
    public List<Restaurante> buscarTodosRestaurantes() {

        List<Restaurante> retornoDoSource = dataSource.buscarTodosRestaurantes();

        return retornoDoSource;

//        List<Restaurante> concreteRestaurants = retornoDTOs.stream().map(dto
//                -> Restaurante.create(dto.id(), dto.nome(), dto.tipoCozinha(), dto.horarioFuncionamento()))
//                .collect(Collectors.toList());
//        return concreteRestaurants;
    }

    @Override
    public Restaurante buscarRestaurantePorNome(String nomeRestaurante) {

        Restaurante retorno = dataSource.buscarRestaurantePorNome(nomeRestaurante);

        return retorno;
    }

    @Override
    public Restaurante buscarRestaurantePorIdentificador(Long id) {

        var retorno = dataSource.buscarRestaurantePorIdentificador(id);

        return retorno;
    }


}
