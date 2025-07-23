package br.com.fiap.restaurante.application.repositories.jpa;

import br.com.fiap.restaurante.application.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
