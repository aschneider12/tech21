package br.com.fiap.restaurante.infra.database.repositories.jpa;

import br.com.fiap.restaurante.infra.database.entities.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Long> {
}
