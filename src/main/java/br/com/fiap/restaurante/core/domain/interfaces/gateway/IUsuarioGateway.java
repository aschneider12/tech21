package br.com.fiap.restaurante.core.domain.interfaces.gateway;

import br.com.fiap.restaurante.core.domain.models.Usuario;

public interface IUsuarioGateway {

    Usuario cadastrar(Usuario usuario);
    boolean deletar(Long id);
    Usuario buscarUsuarioPorIdentificador(Long id);
    Usuario buscarUsuarioPorLogin(String login);
}
