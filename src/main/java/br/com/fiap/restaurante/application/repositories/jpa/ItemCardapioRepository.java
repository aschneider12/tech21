package br.com.fiap.restaurante.application.repositories.jpa;

import br.com.fiap.restaurante.core.domain.entities.ItemCardapio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemCardapioRepository extends JpaRepository<ItemCardapio, Long> {
    Optional<ItemCardapio> findByNome(String nome);
}
