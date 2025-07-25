package br.com.fiap.restaurante.refactor.infraestrutura.database.repository;

import br.com.fiap.restaurante.refactor.infraestrutura.database.entities.TesteRestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TesteRestauranteRepository extends JpaRepository<TesteRestauranteEntity, Long> {

}
