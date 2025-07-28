package br.com.fiap.restaurante.infra.database.repositories.adapter;

import br.com.fiap.restaurante.application.exceptions.EntidadeNaoEncontradaException;
import br.com.fiap.restaurante.application.exceptions.ValidationException;
import br.com.fiap.restaurante.domain.interfaces.storage.IDataStorageUsuario;
import br.com.fiap.restaurante.domain.models.Usuario;
import br.com.fiap.restaurante.infra.database.entities.UsuarioEntity;
import br.com.fiap.restaurante.infra.database.mappers.UsuarioEntityMapper;
import br.com.fiap.restaurante.infra.database.repositories.jpa.UsuarioRepository;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementação concreta do core.
 */
public class UsuarioRepositoryAdapter implements IDataStorageUsuario {

    private final UsuarioRepository repository;

    public UsuarioRepositoryAdapter(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public void atualizarNovaSenha( Long idUsuario, String newPasswordHash) {
        repository.updateNovaSenha(newPasswordHash, idUsuario);
    }

    @Override
    public boolean existsByLogin(String login) {
        return repository.existsByLogin(login);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public Usuario cadastrar(Usuario usuario) {

        UsuarioEntity entity = UsuarioEntityMapper.INSTANCE.toEntity(usuario);

        entity.setDataUltimaAlteracao(LocalDateTime.now());

        entity = repository.save(entity);

        return UsuarioEntityMapper.INSTANCE.toDomain(entity);
    }

    @Override
    public Usuario atualizar(Usuario usuario) {

        UsuarioEntity entity = UsuarioEntityMapper.INSTANCE.toEntity(usuario);

        entity.setDataUltimaAlteracao(LocalDateTime.now());

        entity = repository.save(entity);

        return UsuarioEntityMapper.INSTANCE.toDomain(entity);
    }

    @Override
    public boolean deletar(Long id) {

        if(repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        } else
            return false;
    }

    @Override
    public Usuario buscarUsuarioPorLogin(String login) {

        UsuarioEntity usuarioEntityBD = repository.findByLogin(login)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário", login));

        return UsuarioEntityMapper.INSTANCE.toDomain(usuarioEntityBD);
    }

    @Override
    public Usuario buscarUsuarioPorIdentificador(Long id) {

        UsuarioEntity usuarioEntityBD = repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário", "<ID: "+id+">"));

        return UsuarioEntityMapper.INSTANCE.toDomain(usuarioEntityBD);
    }

    @Override
    public List<Usuario> buscarTodosUsuarios() {


        List<UsuarioEntity> all = repository.findAll(Sort.by(Sort.Direction.DESC, "id"));

        return UsuarioEntityMapper.INSTANCE.toDomain(all);
    }
}