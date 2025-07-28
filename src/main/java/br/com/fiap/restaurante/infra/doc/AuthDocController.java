package br.com.fiap.restaurante.infra.doc;

import br.com.fiap.restaurante.infra.dtos.LoginRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Controller de autenticação de usuários",
        description = "Operações relacionadas a autorização e autenticação de acesso dos usuários")
public interface AuthDocController {

    @Operation(summary = "Autoriza o login do usuário")
    ResponseEntity<String> login(@RequestBody @Valid LoginRequest loginRequest);

    @Operation(summary = "Validar o token enviado.")
    public ResponseEntity<String> validarToken(String authorization);

}
