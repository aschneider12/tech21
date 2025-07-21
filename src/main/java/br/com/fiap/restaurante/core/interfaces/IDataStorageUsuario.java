package br.com.fiap.restaurante.core.interfaces;

import br.com.fiap.restaurante.core.dtos.usuario.UsuarioCadastroDTO;
import br.com.fiap.restaurante.core.dtos.usuario.UsuarioRetornoDTO;

public interface IDataStorageUsuario {

    UsuarioRetornoDTO cadastrar(UsuarioCadastroDTO dto);
    UsuarioRetornoDTO buscarRestaurantePorIdentificador(Long id);
    UsuarioRetornoDTO buscarUsuarioPorLogin(String login);


}
