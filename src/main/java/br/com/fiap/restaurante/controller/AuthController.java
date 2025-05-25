package br.com.fiap.restaurante.controller;

import br.com.fiap.restaurante.dtos.LoginRequest;
import br.com.fiap.restaurante.dtos.MudarSenhaDTO;
import br.com.fiap.restaurante.entities.TipoUsuario;
import br.com.fiap.restaurante.service.UsuarioService;
import br.com.fiap.restaurante.security.JwtService;
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

    @Operation(summary = "Valida o login do usuário")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest loginRequest) {
        String login = loginRequest.getLogin();
        String senha = loginRequest.getSenha();
        TipoUsuario tipoUsuario = loginRequest.getTipoUsuario();

        boolean valido = usuarioService.validarLogin(login, senha, tipoUsuario);
        if (valido) {
            String token = jwtService.gerarToken(login, tipoUsuario);
            return ResponseEntity.ok("Login válido para tipo: " + tipoUsuario + "\nToken: " + token);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas ou tipo incorreto.");
    }

}
