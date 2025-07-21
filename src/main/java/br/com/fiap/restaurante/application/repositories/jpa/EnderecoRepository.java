package br.com.fiap.restaurante.application.repositories.jpa;

import br.com.fiap.restaurante.application.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
