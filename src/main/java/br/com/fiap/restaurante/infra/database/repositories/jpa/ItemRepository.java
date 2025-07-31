package br.com.fiap.restaurante.infra.database.repositories.jpa;

import br.com.fiap.restaurante.infra.database.entities.ItemCardapioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemCardapioEntity, Long> {

    List<ItemCardapioEntity> findAllByRestauranteId(Long restauranteId);

    Long id(Long id);
}
