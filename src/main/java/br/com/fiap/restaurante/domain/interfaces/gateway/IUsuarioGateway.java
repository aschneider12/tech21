package br.com.fiap.restaurante.domain.interfaces.gateway;

import br.com.fiap.restaurante.domain.models.Usuario;

import java.util.List;

public interface IUsuarioGateway {

    Usuario cadastrar(Usuario usuario);
    Usuario atualizar(Usuario usuario);
    boolean deletar(Long id);

    List<Usuario> buscarTodosUsuarios();

    Usuario buscarUsuarioPorIdentificador(Long id);
    Usuario buscarUsuarioPorLogin(String login);

    List<String> buscarPerfisUsuario(Long id);
    void atualizarNovaSenhaUsuario(Long id, String newPasswordHash);

}
