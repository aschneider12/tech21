package br.com.fiap.restaurante.core.interfaces.storage;

import br.com.fiap.restaurante.core.dtos.usuario.UsuarioCadastroDTO;
import br.com.fiap.restaurante.core.dtos.usuario.UsuarioRetornoDTO;

public interface IDataStorageUsuario {

    void atualizarNovaSenha(String novaSenha, Long idUsuario);
    boolean existsByLogin(String login);
    boolean existsByEmail(String email);

    UsuarioRetornoDTO cadastrar(UsuarioCadastroDTO dto);
//TODO, atualizar, deletar

    UsuarioRetornoDTO buscarUsuarioPorLogin(String login);

}
