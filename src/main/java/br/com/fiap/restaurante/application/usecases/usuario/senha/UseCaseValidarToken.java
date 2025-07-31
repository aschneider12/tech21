package br.com.fiap.restaurante.application.usecases.usuario.senha;

import br.com.fiap.restaurante.domain.interfaces.gateway.IUsuarioGateway;
import br.com.fiap.restaurante.infra.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;

public class UseCaseValidarToken {

    @Autowired
    private JwtService jwtService;

    private final IUsuarioGateway gateway;

    private UseCaseValidarToken(IUsuarioGateway gateway) {
        this.gateway = gateway;
    }

    public static UseCaseValidarToken create(IUsuarioGateway gateway) {
        return new UseCaseValidarToken(gateway);
    }

    public boolean run(String authorization) {
        return jwtService.validarToken(authorization);
    }

    public void setJwtService(JwtService jwtService) {
        this.jwtService = jwtService;
    }
}
