package br.com.fiap.restaurante.application.adapter;

import br.com.fiap.restaurante.application.entities.Restaurante;
import br.com.fiap.restaurante.application.repositories.RestauranteRepository;
import br.com.fiap.restaurante.core.dtos.restaurante.RestauranteCadastroDTO;
import br.com.fiap.restaurante.core.dtos.restaurante.RestauranteRetornoDTO;
import br.com.fiap.restaurante.core.interfaces.IDataStorageRestaurante;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RestauranteRepositoryAdapter implements IDataStorageRestaurante {

    private final RestauranteRepository repository;

    public RestauranteRepositoryAdapter(RestauranteRepository repository) {
        this.repository = repository;
    }

    @Override
    public RestauranteRetornoDTO cadastrar(RestauranteCadastroDTO dto) {

        Restaurante saved = new Restaurante(dto);
        repository.save(saved);

        return new RestauranteRetornoDTO(saved.getId(), saved.getNome(), saved.getTipoCozinha(), saved.getHorarioFuncionamento());
    }

    @Override
    public List<RestauranteRetornoDTO> buscarTodosRestaurantes() {

        List<Restaurante> all = repository.findAll();

        return all.stream().map(
                r -> new RestauranteRetornoDTO(r.getId(),r.getNome(),r.getTipoCozinha(),r.getHorarioFuncionamento())).collect(Collectors.toList());
    }

    @Override
    public RestauranteRetornoDTO buscarRestaurantePorIdentificador(Long id) {

        Optional<Restaurante> restaurantDB = repository.findById(id);

        return restaurantDB.map(restaurante ->
                        new RestauranteRetornoDTO(restaurante.getId(), restaurante.getNome()
                            , restaurante.getTipoCozinha(), restaurante.getHorarioFuncionamento()))
                .orElse(null);
    }

    @Override
    public RestauranteRetornoDTO buscarRestaurantePorNome(String nome) {

        Optional<Restaurante> restaurantDB = repository.findByNome(nome);

        return restaurantDB.map(restaurante ->
                        new RestauranteRetornoDTO(restaurante.getId(), restaurante.getNome()
                                , restaurante.getTipoCozinha(), restaurante.getHorarioFuncionamento()))
                .orElse(null);
    }
}
