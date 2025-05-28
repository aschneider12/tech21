package br.com.fiap.restaurante.controller;

import br.com.fiap.restaurante.dtos.MudarSenhaDTO;
import br.com.fiap.restaurante.dtos.UsuarioRequestDTO;
import br.com.fiap.restaurante.dtos.UsuarioResponseDTO;
import br.com.fiap.restaurante.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> cadastrar(@RequestBody @Valid UsuarioRequestDTO dto) {
        UsuarioResponseDTO response = service.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    @Operation(description = "Atualizar usuário existente")
    public ResponseEntity<UsuarioResponseDTO> atualizar(@RequestBody @Valid UsuarioRequestDTO usuarioDTO,
                                                        @PathVariable Long id) {
        UsuarioResponseDTO responseDTO = service.atualizar(id, usuarioDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Deletar usuário")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.ok("Usuário deletado!");
    }

    @GetMapping
    @Operation(description = "Retorna uma lista contendo todos os usuários")
    public ResponseEntity<List<UsuarioResponseDTO>> buscarTodosDTO() {
        List<UsuarioResponseDTO> usuarios = service.buscarTodosDTO();
        return ResponseEntity.ok(usuarios);
    }

    @PatchMapping("/mudar-senha/{id}")
    @Operation(summary = "Altera a senha do usuário")
    public ResponseEntity<Map<String, String>> mudarSenha(@RequestBody MudarSenhaDTO mudarSenhaDTO, @PathVariable Long id) {
        service.mudarSenha(mudarSenhaDTO, id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Senha atualizada com sucesso.");
        return ResponseEntity.ok(response);
    }
}

