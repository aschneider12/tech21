package br.com.fiap.restaurante.infra.controller;

import br.com.fiap.restaurante.infra.dtos.MudarSenhaDTO;
import br.com.fiap.restaurante.infra.dtos.UsuarioInsertDTO;
import br.com.fiap.restaurante.infra.dtos.UsuarioResponseDTO;
import br.com.fiap.restaurante.infra.dtos.UsuarioUpdateDTO;
import br.com.fiap.restaurante.infra.database.entities.UsuarioEntity;
import br.com.fiap.restaurante.infra.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService service;

    @PostMapping
    @Operation(description = "Cadastrar um novo usuário")
    public ResponseEntity<UsuarioResponseDTO> cadastrar(@RequestBody @Valid UsuarioInsertDTO usuarioDTO) {

        UsuarioResponseDTO responseDTO = service.cadastrar(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/{id}")
    @Operation(description = "Atualizar usuário existente.")

    public ResponseEntity<UsuarioResponseDTO> atualizar(@RequestBody @Valid UsuarioUpdateDTO usuarioDTO,
                                                        @PathVariable(required = true) Long id) {

        UsuarioResponseDTO responseDTO  = service.atualizar(id, usuarioDTO);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Deletar usuário.")
    public ResponseEntity<String> deletar(@PathVariable(required = true) Long id) {
        service.deletar(id);
        return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado!");
    }

    @GetMapping
    @Operation(description = "Retorna uma lista contendo todos os usuários.")
    public ResponseEntity<List<UsuarioEntity>> buscarTodos() {
        List<UsuarioEntity> all = service.buscarTodosUsuarios();
        return ResponseEntity.status(HttpStatus.OK).body(all);
    }

    @GetMapping("/{id}")
    @Operation(description = "Buscar usuário por ID.")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable(required = true) Long id) {

        UsuarioResponseDTO dtoResponse = new UsuarioResponseDTO(service.buscarUsuarioPorId(id));
        return ResponseEntity.status(HttpStatus.OK).body(dtoResponse);
    }

    @Operation(summary = "Altera a senha do usuário.")
    @PatchMapping("/mudar-senha/{id}")
    public ResponseEntity<Void> mudarSenha(@RequestBody(required = true) MudarSenhaDTO mudarSenhaDTO, @PathVariable Long id) {
        this.service.mudarSenha(mudarSenhaDTO, id);
        return ResponseEntity.noContent().build();
    }
}
