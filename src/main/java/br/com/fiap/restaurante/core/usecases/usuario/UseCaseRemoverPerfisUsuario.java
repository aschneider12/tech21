package br.com.fiap.restaurante.core.usecases.usuario;

import br.com.fiap.restaurante.domain.domain.interfaces.gateway.IUsuarioGateway;

import java.util.List;

public class UseCaseRemoverPerfisUsuario {

    private final IUsuarioGateway gateway;

    private UseCaseRemoverPerfisUsuario(IUsuarioGateway gateway){
        this.gateway = gateway;
    }

    public static UseCaseRemoverPerfisUsuario create(IUsuarioGateway gateway) {
        return new UseCaseRemoverPerfisUsuario(gateway);
    }

    public void run(Long usuarioId, List<String> perfisDel) {
        throw new RuntimeException("Não implementado!");
    }
}
