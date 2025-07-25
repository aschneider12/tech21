package br.com.fiap.restaurante.infra.controller;

import br.com.fiap.restaurante.infra.dtos.LoginRequest;
import br.com.fiap.restaurante.infra.database.entities.TipoUsuario;
import br.com.fiap.restaurante.infra.security.JwtService;
import br.com.fiap.restaurante.infra.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtService jwtService;

    @Operation(summary = "Autoriza o login do usuário")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest loginRequest) {
        String login = loginRequest.getLogin();
        String senha = loginRequest.getSenha();
        TipoUsuario tipoUsuario = loginRequest.getTipoUsuario();

        boolean valido = usuarioService.validarLogin(login, senha);
        if (valido) {
            String token = jwtService.gerarToken(login, tipoUsuario);
            return ResponseEntity.ok("Login válido para tipo: " + tipoUsuario + "\nToken: " + token);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas ou tipo incorreto.");
    }

    @Operation(summary = "Validar o token enviado.")
    @PostMapping("/validar-token")
    public ResponseEntity<String> validarToken(@RequestHeader String authorization) {

        boolean tokenValido = jwtService.validarToken(authorization);

        if(tokenValido)
            return ResponseEntity.ok("Token é válido.");
        else
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Token enviado é inválido");
    }
}

