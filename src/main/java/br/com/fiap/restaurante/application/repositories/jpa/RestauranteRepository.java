package br.com.fiap.restaurante.application.repositories.jpa;

import br.com.fiap.restaurante.application.entities.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestauranteRepository extends JpaRepository<RestauranteEntity, Long> {

    Optional<RestauranteEntity> findByNome(String nome);
}
