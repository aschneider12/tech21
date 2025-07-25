package br.com.fiap.restaurante.core.usecases.usuario;

import br.com.fiap.restaurante.application.dtos.usuario.UsuarioInputDTO;
import br.com.fiap.restaurante.core.domain.interfaces.gateway.IUsuarioGateway;
import br.com.fiap.restaurante.core.domain.models.Usuario;
import br.com.fiap.restaurante.domain.exceptions.EntidadeJaExisteException;

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

    public Usuario run(UsuarioInputDTO dto) {

        final Usuario usuarioExistente = gateway.buscarUsuarioPorLogin(dto.nome());

        if (usuarioExistente != null)
            throw new EntidadeJaExisteException("Usuário", dto.nome());

        final Usuario novoUsuario = Usuario.fromDTO(dto);

        return gateway.cadastrar(novoUsuario);
    }
}
