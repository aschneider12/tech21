package br.com.fiap.restaurante.core.domain.interfaces.storage;

import br.com.fiap.restaurante.application.dtos.usuario.UsuarioInputDTO;
import br.com.fiap.restaurante.core.domain.models.Usuario;

public interface IDataStorageUsuario {

    void atualizarNovaSenha(String novaSenha, Long idUsuario);
    boolean existsByLogin(String login);
    boolean existsByEmail(String email);

    Usuario cadastrar(UsuarioInputDTO dto);
//TODO, atualizar, deletar

    Usuario buscarUsuarioPorLogin(String login);

}
