package br.com.fiap.restaurante.domain.interfaces.gateway;

import br.com.fiap.restaurante.domain.models.Usuario;

import java.util.List;

public interface IUsuarioGateway {

    Usuario cadastrar(Usuario usuario);
    boolean deletar(Long id);
    Usuario buscarUsuarioPorIdentificador(Long id);
    Usuario buscarUsuarioPorLogin(String login);
    void adicionarPerfisUsuario(Long id);
    List<String> buscarPerfisUsuario(Long id);

}
