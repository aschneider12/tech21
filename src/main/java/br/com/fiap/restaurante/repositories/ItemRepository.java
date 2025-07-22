package br.com.fiap.restaurante.repositories;

import br.com.fiap.restaurante.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
