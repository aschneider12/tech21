package br.com.fiap.restaurante.application.usecases.usuario;

import br.com.fiap.restaurante.application.exceptions.ValidationException;
import br.com.fiap.restaurante.domain.interfaces.gateway.IUsuarioGateway;

public class UseCaseDeletarUsuario {

    private final IUsuarioGateway gateway;

    private UseCaseDeletarUsuario(IUsuarioGateway gateway){
        this.gateway = gateway;
    }

    public static UseCaseDeletarUsuario create(IUsuarioGateway gateway) {
        return new UseCaseDeletarUsuario(gateway);
    }

    public boolean run(Long id) {

        if(!gateway.deletar(id))
            throw new ValidationException("Usuário não deletado!");

        return true;
    }
}
