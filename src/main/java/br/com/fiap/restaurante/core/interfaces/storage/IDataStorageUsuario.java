package br.com.fiap.restaurante.core.interfaces.storage;

import br.com.fiap.restaurante.core.dtos.usuario.UsuarioInputDTO;
import br.com.fiap.restaurante.core.dtos.usuario.UsuarioOutputDTO;

public interface IDataStorageUsuario {

    void atualizarNovaSenha(String novaSenha, Long idUsuario);
    boolean existsByLogin(String login);
    boolean existsByEmail(String email);

    UsuarioOutputDTO cadastrar(UsuarioInputDTO dto);
//TODO, atualizar, deletar

    UsuarioOutputDTO buscarUsuarioPorLogin(String login);

}
