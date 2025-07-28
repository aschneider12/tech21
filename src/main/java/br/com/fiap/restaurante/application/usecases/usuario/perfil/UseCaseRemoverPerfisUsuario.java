package br.com.fiap.restaurante.application.usecases.usuario.perfil;


import br.com.fiap.restaurante.application.exceptions.ValidationException;
import br.com.fiap.restaurante.domain.interfaces.gateway.IUsuarioGateway;
import br.com.fiap.restaurante.domain.models.Usuario;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UseCaseRemoverPerfisUsuario {

    private final IUsuarioGateway gateway;

    private UseCaseRemoverPerfisUsuario(IUsuarioGateway gateway){
        this.gateway = gateway;
    }

    public static UseCaseRemoverPerfisUsuario create(IUsuarioGateway gateway) {
        return new UseCaseRemoverPerfisUsuario(gateway);
    }

    public void run(Long usuarioId, Set<String> perfisDel) {

        if(perfisDel == null || perfisDel.isEmpty())
            throw new ValidationException("Nenhum perfil informado para ser removido.");


        Usuario usuario = gateway.buscarUsuarioPorIdentificador(usuarioId);

        if(usuario.getPerfis() == null)
            usuario.setPerfis(new HashSet<>());

        usuario.getPerfis().removeAll(
                perfisDel
        );

        gateway.atualizar(usuario);
    }
}
