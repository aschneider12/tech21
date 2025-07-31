package br.com.fiap.restaurante.application.usecases.usuario;

import br.com.fiap.restaurante.application.input.UsuarioInput;
import br.com.fiap.restaurante.application.output.UsuarioOutput;
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

    public UsuarioOutput run(UsuarioInput input) {

        final Usuario usuarioExistente = gateway.buscarUsuarioPorLogin(input.login());

        if (usuarioExistente != null)
            throw new EntidadeJaExisteException("Usuário", input.login());

        var usuarioCadastrar = UsuarioInput.toDomain(input);


        usuarioCadastrar.validacoesDominio();

        Usuario cadastrado = gateway.cadastrar(usuarioCadastrar);

        return UsuarioOutput.fromDomain(cadastrado);
    }
}
