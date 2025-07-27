package br.com.fiap.restaurante.application.usecases.usuario;

import br.com.fiap.restaurante.domain.interfaces.gateway.IUsuarioGateway;

import java.util.List;

public class UseCaseAdicionarPerfisUsuario {

    private final IUsuarioGateway gateway;

    private UseCaseAdicionarPerfisUsuario(IUsuarioGateway gateway){
        this.gateway = gateway;
    }

    public static UseCaseAdicionarPerfisUsuario create(IUsuarioGateway gateway) {
        return new UseCaseAdicionarPerfisUsuario(gateway);
    }

    public void run(Long usuarioId, List<String> perfisAdd) {

        throw new RuntimeException("Não implementado!");
    }
}
