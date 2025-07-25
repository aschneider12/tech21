package br.com.fiap.restaurante.infra.database.repositories.jpa;

import br.com.fiap.restaurante.infra.database.entities.UsuarioEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE usuario SET senha = :senha WHERE id = :id", nativeQuery = true)
    void updateNovaSenha(@Param("senha") String senhaNova, @Param("id") Long id);

    boolean existsByLogin(String login);

    boolean existsByEmail(String email);

    Optional<UsuarioEntity> findByLogin(String login);
}
