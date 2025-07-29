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

        //validar existencia de outros usuarios
        //validar senha forte

        Usuario cadastrado = gateway.cadastrar(UsuarioInput.toDomain(input));

        return UsuarioOutput.fromDomain(cadastrado);
    }
}
