package br.com.fiap.restaurante.application.usecases.usuario.senha;

import br.com.fiap.restaurante.domain.interfaces.gateway.IUsuarioGateway;
import br.com.fiap.restaurante.infra.security.JwtService;

public class UseCaseValidarToken {

    private final JwtService jwtService;
    private final IUsuarioGateway gateway;

    private UseCaseValidarToken(IUsuarioGateway gateway, JwtService jwtService){
        this.gateway = gateway;
        this.jwtService = jwtService;
    }

    public static UseCaseValidarToken create(IUsuarioGateway gateway, JwtService jwtService) {
        return new UseCaseValidarToken(gateway, jwtService);
    }

    public boolean run(String authorization){
        return jwtService.validarToken(authorization);
    }
}

