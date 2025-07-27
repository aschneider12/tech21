package br.com.fiap.restaurante.application.usecases.usuario;

import br.com.fiap.restaurante.domain.interfaces.gateway.IUsuarioGateway;
import br.com.fiap.restaurante.domain.models.Usuario;

public class UseCaseBuscarUsuarioPorID {

    private final IUsuarioGateway gateway;

    private UseCaseBuscarUsuarioPorID(IUsuarioGateway gateway) {
        this.gateway = gateway;
    }

    public static UseCaseBuscarUsuarioPorID create(IUsuarioGateway gateway) {
        return new UseCaseBuscarUsuarioPorID(gateway);
    }

    public Usuario run(Long id) {

        gateway.buscarUsuarioPorIdentificador(id);

        return null;
    }

}
