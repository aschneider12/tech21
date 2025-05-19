package br.com.fiap.restaurante.repositories;

import br.com.fiap.restaurante.entities.Usuario;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 Camada de acesso aos dados salvos.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE usuario SET senha = :senha WHERE id = :id", nativeQuery = true)
    public void mudarSenha(@Param("senha") String senhaNova, @Param("id") Long id);


    boolean existsByLogin(String login);

    boolean existsByEmail(String email);

}
