package br.com.fiap.restaurante.domain.interfaces.storage;

import br.com.fiap.restaurante.application.input.UsuarioInput;
import br.com.fiap.restaurante.application.output.UsuarioOutput;
import br.com.fiap.restaurante.domain.models.Usuario;

public interface IDataStorageUsuario {

    void atualizarNovaSenha(String novaSenha, Long idUsuario);
    boolean existsByLogin(String login);
    boolean existsByEmail(String email);

    Usuario cadastrar(Usuario dto);
//TODO, atualizar, deletar

    Usuario buscarUsuarioPorLogin(String login);

}
