package br.com.fiap.restaurante.application.usecases.usuario.senha;

import br.com.fiap.restaurante.infra.security.JwtService;

public class UseCaseValidarToken {

    private final JwtService jwtService;

    private UseCaseValidarToken( JwtService jwtService){
        this.jwtService = jwtService;
    }

    public static UseCaseValidarToken create(JwtService jwtService) {
        return new UseCaseValidarToken(jwtService);
    }

    public boolean run(String authorization){
        return jwtService.validarToken(authorization);
    }
}

