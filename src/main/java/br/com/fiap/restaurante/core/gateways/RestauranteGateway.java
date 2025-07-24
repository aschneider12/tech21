package br.com.fiap.restaurante.core.gateways;

import br.com.fiap.restaurante.core.domain.entities.Restaurante;
import br.com.fiap.restaurante.core.dtos.restaurante.RestauranteInputDTO;
import br.com.fiap.restaurante.core.dtos.restaurante.RestauranteOutputDTO;
import br.com.fiap.restaurante.core.interfaces.gateway.IRestauranteGateway;
import br.com.fiap.restaurante.core.interfaces.storage.IDataStorageRestaurante;
import br.com.fiap.restaurante.core.mappers.RestauranteMapper;

import java.util.List;
import java.util.stream.Collectors;

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

        var input = RestauranteMapper.toInput(restaurante);

        final RestauranteOutputDTO dtoCadastrado = this.dataSource.cadastrar(input);

        return RestauranteMapper.toDomain(dtoCadastrado);
    }

    @Override
    public Restaurante atualizar(Restaurante restaurante) {
        var input = RestauranteMapper.toInput(restaurante);

        RestauranteOutputDTO dtoAtualizado = this.dataSource.atualizar(input);

        return RestauranteMapper.toDomain(dtoAtualizado);
    }

    @Override
    public boolean deletar(Long id) {
        return this.dataSource.deletar(id);
    }

    @Override
    public List<Restaurante> buscarTodosRestaurantes() {

        List<RestauranteOutputDTO> retornoDTOs = dataSource.buscarTodosRestaurantes();

        return RestauranteMapper.toDomain(retornoDTOs);

//        List<Restaurante> concreteRestaurants = retornoDTOs.stream().map(dto
//                -> Restaurante.create(dto.id(), dto.nome(), dto.tipoCozinha(), dto.horarioFuncionamento()))
//                .collect(Collectors.toList());
//        return concreteRestaurants;
    }

    @Override
    public Restaurante buscarRestaurantePorNome(String nomeRestaurante) {

        RestauranteOutputDTO retornoDTO = dataSource.buscarRestaurantePorNome(nomeRestaurante);

        return RestauranteMapper.toDomain(retornoDTO);
    }

    @Override
    public Restaurante buscarRestaurantePorIdentificador(Long id) {

        RestauranteOutputDTO retornoDTO = dataSource.buscarRestaurantePorIdentificador(id);

        return RestauranteMapper.toDomain(retornoDTO);
    }


}
