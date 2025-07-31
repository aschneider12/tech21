package br.com.fiap.restaurante.application.usecases.usuario;

import br.com.fiap.restaurante.application.exceptions.EntidadeNaoEncontradaException;
import br.com.fiap.restaurante.application.input.UsuarioInput;
import br.com.fiap.restaurante.application.output.UsuarioOutput;
import br.com.fiap.restaurante.domain.interfaces.gateway.IUsuarioGateway;
import br.com.fiap.restaurante.domain.models.Usuario;

/**
 * Realilza a atualização de um usuario.
 */
public class UseCaseAtualizarUsuario {

    private final IUsuarioGateway gateway;

    private UseCaseAtualizarUsuario(IUsuarioGateway gateway){
        this.gateway = gateway;
    }

    public static UseCaseAtualizarUsuario create(IUsuarioGateway gateway) {
        return new UseCaseAtualizarUsuario(gateway);
    }

    public UsuarioOutput run(Long usuarioId, UsuarioInput input) {

        final Usuario usuarioExistente = gateway.buscarUsuarioPorIdentificador(usuarioId);

        if (usuarioExistente == null)
            throw new EntidadeNaoEncontradaException("Usuário", "ID <"+usuarioId+">");

        var usuarioAtualizar = UsuarioInput.toDomain(input);

        //* campos não atualizados mais via usuários, e sim por UC's próprios *//
        usuarioAtualizar.setId(usuarioId);
        usuarioAtualizar.setPerfis(usuarioExistente.getPerfis());
        usuarioAtualizar.setSenha(usuarioExistente.getSenha());

        usuarioAtualizar.validacoesDominio();

        Usuario atualizado = gateway.atualizar(usuarioAtualizar);

        return UsuarioOutput.fromDomain(atualizado);
    }
}
