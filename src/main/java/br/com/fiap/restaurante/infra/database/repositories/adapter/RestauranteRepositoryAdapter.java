package br.com.fiap.restaurante.infra.database.repositories.adapter;

import br.com.fiap.restaurante.core.domain.interfaces.storage.IDataStorageRestaurante;
import br.com.fiap.restaurante.core.domain.models.Restaurante;
import br.com.fiap.restaurante.domain.exceptions.EntidadeNaoEncontradaException;
import br.com.fiap.restaurante.infra.database.entities.RestauranteEntity;
import br.com.fiap.restaurante.infra.database.mappers.RestauranteEntityMapper;
import br.com.fiap.restaurante.infra.database.repositories.jpa.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

/**
 * Implementação concreta do core.
 */
public class RestauranteRepositoryAdapter implements IDataStorageRestaurante {

    @Autowired
    private RestauranteEntityMapper restauranteMapper;

    private final RestauranteRepository repository;

    public RestauranteRepositoryAdapter(RestauranteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Restaurante cadastrar(Restaurante restauranteDomain) {


        var entitySaved = repository.save(restauranteMapper.toEntity(restauranteDomain));

        return restauranteMapper.toDomain(entitySaved);
    }

    @Override
    public Restaurante atualizar(Restaurante restauranteDomain) {
        if(restauranteDomain.getId() == null)
            throw new RuntimeException("Não é possível atualizar a entidade sem ID");//TODO - customizar exception quando nao é permitido

        var entityUpdated = repository.save(restauranteMapper.toEntity(restauranteDomain));

        return restauranteMapper.toDomain(entityUpdated);
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
    public List<Restaurante> buscarTodosRestaurantes() {

        List<RestauranteEntity> all = repository.findAll();

        return restauranteMapper.toDomain(all);
//
//        return all.stream().map(
//                r -> new RestauranteOutputDTO(r.getId(),r.getNome(),r.getTipoCozinha(),r.getHorarioFuncionamento())).collect(Collectors.toList());
    }

    @Override
    public Restaurante buscarRestaurantePorIdentificador(Long id) {

        Optional<RestauranteEntity> restaurantDB = repository.findById(id);

        if(restaurantDB.isPresent())
            return restauranteMapper.toDomain(restaurantDB.get());

        else return null;
    }

    @Override
    public Restaurante buscarRestaurantePorNome(String nome) {

        Optional<RestauranteEntity> restaurantDB = repository.findByNome(nome);

        if(restaurantDB.isPresent())
            return restauranteMapper.toDomain(restaurantDB.get());

        else return null;
    }
}
