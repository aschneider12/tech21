package br.com.fiap.restaurante.core.interfaces.gateway;

import br.com.fiap.restaurante.core.domain.entities.Usuario;

public interface IUsuarioGateway {

    Usuario cadastrar(Usuario usuario);
    boolean deletar(Long id);
    Usuario buscarUsuarioPorIdentificador(Long id);
    Usuario buscarUsuarioPorLogin(String login);
}
