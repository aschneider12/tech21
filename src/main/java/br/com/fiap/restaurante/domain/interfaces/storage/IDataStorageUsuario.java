package br.com.fiap.restaurante.domain.interfaces.storage;

import br.com.fiap.restaurante.application.input.UsuarioInput;
import br.com.fiap.restaurante.application.output.UsuarioOutput;
import br.com.fiap.restaurante.domain.models.Usuario;

import java.util.List;

public interface IDataStorageUsuario {

    void atualizarNovaSenha(Long idUsuario, String newPasswordHash);
    boolean existsByLogin(String login);
    boolean existsByEmail(String email);

    Usuario cadastrar(Usuario usuario);
    Usuario atualizar(Usuario usuario);
    boolean deletar(Long id);

    Usuario buscarUsuarioPorLogin(String login);
    Usuario buscarUsuarioPorIdentificador(Long id);
    List<Usuario> buscarTodosUsuarios();

}
