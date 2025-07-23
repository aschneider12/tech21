package br.com.fiap.restaurante.application.repositories.adapter;

import br.com.fiap.restaurante.application.entities.RestauranteEntity;
import br.com.fiap.restaurante.application.repositories.jpa.RestauranteRepository;
import br.com.fiap.restaurante.core.dtos.restaurante.RestauranteInputDTO;
import br.com.fiap.restaurante.core.dtos.restaurante.RestauranteOutputDTO;
import br.com.fiap.restaurante.core.interfaces.storage.IDataStorageRestaurante;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementação concreta do core.
 */
public class RestauranteRepositoryAdapter implements IDataStorageRestaurante  {

    private final RestauranteRepository repository;

    public RestauranteRepositoryAdapter(RestauranteRepository repository) {
        this.repository = repository;
    }

    @Override
    public RestauranteOutputDTO cadastrar(RestauranteInputDTO dto) {

        RestauranteEntity saved = new RestauranteEntity(dto);
        repository.save(saved);

        return new RestauranteOutputDTO(saved.getId(), saved.getNome(), saved.getTipoCozinha(), saved.getHorarioFuncionamento());
    }

    @Override
    public List<RestauranteOutputDTO> buscarTodosRestaurantes() {

        List<RestauranteEntity> all = repository.findAll();

        return all.stream().map(
                r -> new RestauranteOutputDTO(r.getId(),r.getNome(),r.getTipoCozinha(),r.getHorarioFuncionamento())).collect(Collectors.toList());
    }

    @Override
    public RestauranteOutputDTO buscarRestaurantePorIdentificador(Long id) {

        Optional<RestauranteEntity> restaurantDB = repository.findById(id);

        return restaurantDB.map(restaurante ->
                        new RestauranteOutputDTO(restaurante.getId(), restaurante.getNome()
                            , restaurante.getTipoCozinha(), restaurante.getHorarioFuncionamento()))
                .orElse(null);
    }

    @Override
    public RestauranteOutputDTO buscarRestaurantePorNome(String nome) {

        Optional<RestauranteEntity> restaurantDB = repository.findByNome(nome);

        return restaurantDB.map(restaurante ->
                        new RestauranteOutputDTO(restaurante.getId(), restaurante.getNome()
                                , restaurante.getTipoCozinha(), restaurante.getHorarioFuncionamento()))
                .orElse(null);
    }
}
