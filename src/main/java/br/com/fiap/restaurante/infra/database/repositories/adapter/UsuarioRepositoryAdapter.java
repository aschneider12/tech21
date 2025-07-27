package br.com.fiap.restaurante.infra.database.repositories.adapter;

import br.com.fiap.restaurante.domain.interfaces.storage.IDataStorageUsuario;
import br.com.fiap.restaurante.domain.models.Usuario;
import br.com.fiap.restaurante.infra.database.entities.UsuarioEntity;
import br.com.fiap.restaurante.infra.database.repositories.jpa.UsuarioRepository;

/**
 * Implementação concreta do core.
 */
public class UsuarioRepositoryAdapter implements IDataStorageUsuario {

    private final UsuarioRepository repository;

    public UsuarioRepositoryAdapter(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public void atualizarNovaSenha(String novaSenha, Long idUsuario) {
        repository.updateNovaSenha(novaSenha,idUsuario);
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
    public Usuario cadastrar(Usuario dto) {
/// TODO/        Map to UsuarioEntity;


         repository.save(new UsuarioEntity());
         return null;
    }

    @Override
    public Usuario buscarUsuarioPorLogin(String login) {
        return null;
    }
}
