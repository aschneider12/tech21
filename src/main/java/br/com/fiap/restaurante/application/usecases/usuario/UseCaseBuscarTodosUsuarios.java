package br.com.fiap.restaurante.application.usecases.usuario;

import br.com.fiap.restaurante.application.output.UsuarioOutput;
import br.com.fiap.restaurante.domain.interfaces.gateway.IUsuarioGateway;
import br.com.fiap.restaurante.domain.models.Usuario;

import java.util.List;

public class UseCaseBuscarTodosUsuarios {

    private final IUsuarioGateway gateway;

    private UseCaseBuscarTodosUsuarios(IUsuarioGateway gateway) {
        this.gateway = gateway;
    }

    public static UseCaseBuscarTodosUsuarios create(IUsuarioGateway gateway) {
        return new UseCaseBuscarTodosUsuarios(gateway);
    }

    public List<UsuarioOutput> run() {

        List<Usuario> usuarios = gateway.buscarTodosUsuarios();

        return UsuarioOutput.fromDomain(usuarios);
    }

}
