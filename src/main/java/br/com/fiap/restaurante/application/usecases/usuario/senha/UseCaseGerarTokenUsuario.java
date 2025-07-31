package br.com.fiap.restaurante.application.usecases.usuario.senha;

import br.com.fiap.restaurante.domain.interfaces.gateway.IUsuarioGateway;
import br.com.fiap.restaurante.infra.security.JwtService;

public class UseCaseGerarTokenUsuario {

    private JwtService jwtService;

    private final IUsuarioGateway gateway;

    private UseCaseGerarTokenUsuario(IUsuarioGateway gateway, JwtService jwtService){
        this.jwtService = jwtService;
        this.gateway = gateway;
    }

    public static UseCaseGerarTokenUsuario create(IUsuarioGateway gateway, JwtService jwtService) {
        return new UseCaseGerarTokenUsuario(gateway, jwtService);
    }
    public String run(String login){

         return jwtService.gerarToken(login);
    }
}