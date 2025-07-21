package br.com.fiap.restaurante.application.repositories;

import br.com.fiap.restaurante.application.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
