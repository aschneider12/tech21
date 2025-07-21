package br.com.fiap.restaurante.application.repositories.jpa;

import br.com.fiap.restaurante.application.entities.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

    Optional<Restaurante> findByNome(String nome);
}
