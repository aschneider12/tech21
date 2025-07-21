package br.com.fiap.restaurante.core.interfaces;

import br.com.fiap.restaurante.core.domain.entities.Usuario;

public interface IUsuarioGateway {

    Usuario cadastrar(Usuario usuario);
    Usuario buscarUsuarioPorIdentificador(Long id);
    Usuario buscarUsuarioPorLogin(String login);
}
