package br.com.fiap.restaurante.controller;

import br.com.fiap.restaurante.DTO.MudarSenhaDTO;
import br.com.fiap.restaurante.entities.Usuario;
import br.com.fiap.restaurante.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService service;

    //TODO - padronizar um meio de resposta aqui, enviando um objeto (status,msg,obj)

    @PostMapping
    @Operation(description = "Cadastrar um novo usuário.")
    public ResponseEntity<Usuario> cadastrar(@RequestBody @Valid Usuario usuario) {
        Usuario salvo = service.salvar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping
    @Operation(description = "Atualizar usuário existente.")
    public ResponseEntity<Usuario> atualizar(@RequestBody @Valid Usuario usuario) {
        Usuario atualizado = service.salvar(usuario);
        return ResponseEntity.status(HttpStatus.OK).body(atualizado);
    }

    @DeleteMapping
    @Operation(description = "Deletar usuário.")
    public ResponseEntity<?> atualizar(@RequestParam Long id) {
        service.deletar(id);
        return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado!");
    }

    @GetMapping
    @Operation(description = "Retorna uma lista contendo todos os usuários.")
    public ResponseEntity<List<Usuario>> buscarTodos() {
        List<Usuario> all = service.buscarTodosUsuarios();
        return ResponseEntity.status(HttpStatus.OK).body(all);
    }

    @PatchMapping("/mudar-senha/{id}")
    public ResponseEntity<Void> mudarSenha(@RequestBody MudarSenhaDTO mudarSenhaDTO, @PathVariable Long id) {
        this.service.mudarSenha(mudarSenhaDTO, id);
        return ResponseEntity.noContent().build();
    }
}
