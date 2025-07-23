package br.com.fiap.restaurante.application.repositories.adapter;

import br.com.fiap.restaurante.application.entities.Usuario;
import br.com.fiap.restaurante.application.repositories.jpa.UsuarioRepository;
import br.com.fiap.restaurante.core.dtos.usuario.UsuarioCadastroDTO;
import br.com.fiap.restaurante.core.dtos.usuario.UsuarioRetornoDTO;
import br.com.fiap.restaurante.core.interfaces.storage.IDataStorageUsuario;

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
    public UsuarioRetornoDTO cadastrar(UsuarioCadastroDTO dto) {
//        Map to UsuarioEntity;

         repository.save(new Usuario());
         return null;
    }

    @Override
    public UsuarioRetornoDTO buscarUsuarioPorLogin(String login) {
        return null;
    }
}
