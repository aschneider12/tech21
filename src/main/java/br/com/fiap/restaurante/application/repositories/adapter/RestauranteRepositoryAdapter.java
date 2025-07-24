package br.com.fiap.restaurante.application.repositories.adapter;

import br.com.fiap.restaurante.application.entities.RestauranteEntity;
import br.com.fiap.restaurante.application.mappers.RestauranteMapper;
import br.com.fiap.restaurante.application.repositories.jpa.RestauranteRepository;
import br.com.fiap.restaurante.core.dtos.restaurante.RestauranteInputDTO;
import br.com.fiap.restaurante.core.dtos.restaurante.RestauranteOutputDTO;
import br.com.fiap.restaurante.core.exceptions.EntidadeNaoEncontradaException;
import br.com.fiap.restaurante.core.interfaces.storage.IDataStorageRestaurante;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

/**
 * Implementação concreta do core.
 */
public class RestauranteRepositoryAdapter implements IDataStorageRestaurante  {

    @Autowired
    private RestauranteMapper restauranteMapper;

    private final RestauranteRepository repository;

    public RestauranteRepositoryAdapter(RestauranteRepository repository) {
        this.repository = repository;
    }

    @Override
    public RestauranteOutputDTO cadastrar(RestauranteInputDTO dto) {

        var entitySaved = repository.save(restauranteMapper.toEntity(dto));

        return restauranteMapper.toOutputDomain(entitySaved);
    }

    @Override
    public RestauranteOutputDTO atualizar(RestauranteInputDTO dto) {
        if(dto.id() == null)
            throw new RuntimeException("Não é possível atualizar a entidade sem ID");//TODO - customizar exception quando nao é permitido

        var entityUpdated = repository.save(restauranteMapper.toEntity(dto));

        return restauranteMapper.toOutputDomain(entityUpdated);
    }

    @Override
    public boolean deletar(Long id) {

        if(repository.existsById(id))
            repository.deleteById(id);
        else
            throw new EntidadeNaoEncontradaException("Restaurante", "ID - "+id);

        return false;//FALSE ou EXCEPTION?
    }

    @Override
    public List<RestauranteOutputDTO> buscarTodosRestaurantes() {

        List<RestauranteEntity> all = repository.findAll();

        return restauranteMapper.toOutputDomain(all);
//
//        return all.stream().map(
//                r -> new RestauranteOutputDTO(r.getId(),r.getNome(),r.getTipoCozinha(),r.getHorarioFuncionamento())).collect(Collectors.toList());
    }

    @Override
    public RestauranteOutputDTO buscarRestaurantePorIdentificador(Long id) {

        Optional<RestauranteEntity> restaurantDB = repository.findById(id);

        if(restaurantDB.isPresent())
            return restauranteMapper.toOutputDomain(restaurantDB.get());

        else return null;
    }

    @Override
    public RestauranteOutputDTO buscarRestaurantePorNome(String nome) {

        Optional<RestauranteEntity> restaurantDB = repository.findByNome(nome);

        if(restaurantDB.isPresent())
            return restauranteMapper.toOutputDomain(restaurantDB.get());

        else return null;
    }
}
