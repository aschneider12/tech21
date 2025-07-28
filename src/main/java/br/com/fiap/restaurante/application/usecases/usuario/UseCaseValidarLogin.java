package br.com.fiap.restaurante.application.usecases.usuario;

import br.com.fiap.restaurante.application.exceptions.EntidadeNaoEncontradaException;
import br.com.fiap.restaurante.domain.interfaces.gateway.IUsuarioGateway;
import br.com.fiap.restaurante.domain.models.Usuario;
import br.com.fiap.restaurante.infra.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UseCaseValidarLogin {

    private final PasswordEncoder passwordEncoder;
    private final IUsuarioGateway gateway;

    private UseCaseValidarLogin(IUsuarioGateway gateway, PasswordEncoder passwordEncoder){
        this.gateway = gateway;
        this.passwordEncoder = passwordEncoder;
    }

    public static UseCaseValidarLogin create(IUsuarioGateway gateway, PasswordEncoder passwordEncoder) {
        return new UseCaseValidarLogin(gateway,passwordEncoder);
    }
    public Boolean run(String login, String senha){

        try {
            Usuario usuario = gateway.buscarUsuarioPorLogin(login);

            return passwordEncoder.matches(senha, usuario.getSenha());

        } catch(EntidadeNaoEncontradaException ex ) {

            return false;
        }
    }
}
