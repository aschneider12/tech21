package br.com.fiap.restaurante.infra.database.repositories.jpa;

import br.com.fiap.restaurante.infra.database.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
