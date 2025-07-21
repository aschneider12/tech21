package br.com.fiap.restaurante.core.domain.usecases.usuario;

import br.com.fiap.restaurante.core.domain.entities.Usuario;
import br.com.fiap.restaurante.core.dtos.usuario.UsuarioCadastroDTO;
import br.com.fiap.restaurante.core.exceptions.EntidadeJaExisteException;
import br.com.fiap.restaurante.core.interfaces.IUsuarioGateway;

/**
 * Realilza o cadastro de um novo usuario.
 */
public class UseCaseCadastrarUsuario {

    private final IUsuarioGateway gateway;

    private UseCaseCadastrarUsuario(IUsuarioGateway gateway){
        this.gateway = gateway;
    }

    public static UseCaseCadastrarUsuario create(IUsuarioGateway gateway) {
        return new UseCaseCadastrarUsuario(gateway);
    }

    public Usuario run(UsuarioCadastroDTO dto) {

        final Usuario usuarioExistente = gateway.buscarUsuarioPorLogin(dto.nome());

        if (usuarioExistente != null)
            throw new EntidadeJaExisteException("Usuário", dto.nome());

        final Usuario novoUsuario = Usuario.fromDTO(dto);

        return gateway.cadastrar(novoUsuario);
    }
}
