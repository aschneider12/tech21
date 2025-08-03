package br.com.fiap.restaurante.infra.controller;

import br.com.fiap.restaurante.application.gateways.UsuarioGateway;
import br.com.fiap.restaurante.application.usecases.usuario.FactoryUsuarioUseCase;
import br.com.fiap.restaurante.application.usecases.usuario.senha.UseCaseGerarTokenUsuario;
import br.com.fiap.restaurante.application.usecases.usuario.senha.UseCaseValidarLogin;
import br.com.fiap.restaurante.application.usecases.usuario.senha.UseCaseValidarToken;
import br.com.fiap.restaurante.infra.database.repositories.adapter.UsuarioRepositoryAdapter;
import br.com.fiap.restaurante.infra.database.repositories.jpa.UsuarioRepository;
import br.com.fiap.restaurante.infra.doc.AuthDocController;
import br.com.fiap.restaurante.infra.dtos.LoginRequest;
import br.com.fiap.restaurante.infra.security.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController implements AuthDocController {

//    private JwtService jwtService;
//    private PasswordEncoder passwordEncoder;
//    private UsuarioGateway gateway;
    FactoryUsuarioUseCase factory;

    public AuthController(FactoryUsuarioUseCase factory) {
        this.factory = factory;
//        this.jwtService = jwtService;
//        this.gateway = UsuarioGateway.create(new UsuarioRepositoryAdapter(repository));
    }

    @Override
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest loginRequest) {

        String login = loginRequest.getLogin();
        String senha = loginRequest.getSenha();

        var ucValidarLogin = factory.validarLogin();
        boolean valido = ucValidarLogin.run(login, senha);
        if (valido) {

            var ucGerarToken = factory.gerarToken();
            String token = ucGerarToken.run(login);

            return ResponseEntity.ok("Usuário: " + login + "\nToken: " + token);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas ou usuário incorreto.");
    }

    @Override
    @PostMapping("/validar-token")
    public ResponseEntity<String> validarToken(@RequestHeader String authorization) {

        var ucValidarToken = factory.validarToken();
        boolean tokenValido = ucValidarToken.run(authorization);

        if(tokenValido)
            return ResponseEntity.ok("Token é válido.");
        else
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Token enviado é inválido");
    }
}


