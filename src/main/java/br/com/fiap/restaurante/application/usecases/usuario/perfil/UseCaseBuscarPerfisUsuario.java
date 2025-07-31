package br.com.fiap.restaurante.application.usecases.usuario.perfil;

import br.com.fiap.restaurante.domain.interfaces.gateway.IUsuarioGateway;
import br.com.fiap.restaurante.domain.models.Usuario;

import java.util.List;
import java.util.Set;

public class UseCaseBuscarPerfisUsuario {

    private final IUsuarioGateway gateway;

    private UseCaseBuscarPerfisUsuario(IUsuarioGateway gateway){
        this.gateway = gateway;
    }

    public static UseCaseBuscarPerfisUsuario create(IUsuarioGateway gateway) {
        return new UseCaseBuscarPerfisUsuario(gateway);
    }

    public Set<String> run(Long usuarioId) {

        Usuario usuario = gateway.buscarUsuarioPorIdentificador(usuarioId);
        return usuario.getPerfis();
    }
}
