package br.com.fiap.restaurante.application.usecases.usuario.perfil;

import br.com.fiap.restaurante.domain.interfaces.gateway.IUsuarioGateway;
import br.com.fiap.restaurante.domain.models.Usuario;

import java.util.HashSet;
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

        Usuario usuario = gateway.buscarUsuarioPorIdentificador(usuarioId);

        //ver se existem dentro do enum

        if(usuario.getPerfis() == null)
            usuario.setPerfis(new HashSet<>());

        usuario.getPerfis().addAll(
                perfisAdd
        );

        gateway.atualizar(usuario);
    }
}
