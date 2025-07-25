package br.com.fiap.restaurante.infra.database.repositories.jpa;

import br.com.fiap.restaurante.infra.database.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
