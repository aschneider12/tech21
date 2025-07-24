package br.com.fiap.restaurante.application.repositories.jpa;

import br.com.fiap.restaurante.application.entities.ItemCardapioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemCardapioRepository extends JpaRepository<ItemCardapioEntity, Long> {
    Optional<ItemCardapioEntity> findByNome(String nome);
}
