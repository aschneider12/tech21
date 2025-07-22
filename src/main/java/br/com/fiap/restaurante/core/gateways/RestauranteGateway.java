package br.com.fiap.restaurante.core.gateways;

import br.com.fiap.restaurante.core.domain.entities.Restaurante;
import br.com.fiap.restaurante.core.dtos.restaurante.RestauranteInputDTO;
import br.com.fiap.restaurante.core.dtos.restaurante.RestauranteOutputDTO;
import br.com.fiap.restaurante.core.interfaces.gateway.IRestauranteGateway;
import br.com.fiap.restaurante.core.interfaces.storage.IDataStorageRestaurante;

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

        RestauranteInputDTO dto = RestauranteInputDTO.fromEntity(restaurante);

        final RestauranteOutputDTO rc = this.dataSource.cadastrar(dto);

        return Restaurante.create(rc.id(), rc.nome(), rc.tipoCozinha(), rc.horarioFuncionamento());
    }

    @Override
    public List<Restaurante> buscarTodosRestaurantes() {

        List<RestauranteOutputDTO> retornoDTOs = dataSource.buscarTodosRestaurantes();

        List<Restaurante> concreteRestaurants = retornoDTOs.stream().map(dto
                -> Restaurante.create(dto.id(), dto.nome(), dto.tipoCozinha(), dto.horarioFuncionamento()))
                .collect(Collectors.toList());

        return concreteRestaurants;
    }

    @Override
    public Restaurante buscarRestaurantePorNome(String nomeRestaurante) {
        RestauranteOutputDTO retornoDTO = dataSource.buscarRestaurantePorNome(nomeRestaurante);

        return Restaurante.create(retornoDTO.id(), retornoDTO.nome(), retornoDTO.tipoCozinha(),
          retornoDTO.horarioFuncionamento());
    }

    @Override
    public Restaurante buscarRestaurantePorIdentificador(Long id) {

        RestauranteOutputDTO retornoDTO = dataSource.buscarRestaurantePorIdentificador(id);

        return Restaurante.create(retornoDTO.id(), retornoDTO.nome(), retornoDTO.tipoCozinha(),
                retornoDTO.horarioFuncionamento());

    }


}
