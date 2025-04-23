package br.com.fiap.restaurante.repositories;

import br.com.fiap.restaurante.entities.Usuario;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 Camada de acesso aos dados salvos.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
