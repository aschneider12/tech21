package br.com.fiap.restaurante.application.usecases.usuario;

import br.com.fiap.restaurante.application.input.UsuarioInput;
import br.com.fiap.restaurante.domain.interfaces.gateway.IUsuarioGateway;
import br.com.fiap.restaurante.domain.models.Usuario;
import br.com.fiap.restaurante.application.exceptions.EntidadeJaExisteException;

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

    public Usuario run(UsuarioInput input) {

        final Usuario usuarioExistente = gateway.buscarUsuarioPorLogin(input.nome());

        if (usuarioExistente != null)
            throw new EntidadeJaExisteException("Usuário", input.nome());

        final Usuario novoUsuario = UsuarioInput.toDomain(input);

        return gateway.cadastrar(novoUsuario);
    }
}
