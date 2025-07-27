package br.com.fiap.restaurante.application.usecases.usuario;

import br.com.fiap.restaurante.domain.interfaces.gateway.IUsuarioGateway;

import java.util.List;

public class UseCaseBuscarPerfisUsuario {

    private final IUsuarioGateway gateway;

    private UseCaseBuscarPerfisUsuario(IUsuarioGateway gateway){
        this.gateway = gateway;
    }

    public static UseCaseBuscarPerfisUsuario create(IUsuarioGateway gateway) {
        return new UseCaseBuscarPerfisUsuario(gateway);
    }

    public List<String> run(Long usuarioId) {

//        gateway.buscarTodosPerfisUsuario(usuarioId);
        throw new RuntimeException("Não implementado!");
    }
}
