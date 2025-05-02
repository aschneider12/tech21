package br.com.fiap.restaurante.controller;

import br.com.fiap.restaurante.entities.Usuario;
import br.com.fiap.restaurante.service.UsuarioService;
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
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {
        Usuario salvo = service.salvar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping
    public ResponseEntity<Usuario> atualizar(@RequestBody Usuario usuario) {
        Usuario atualizado = service.salvar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(atualizado);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> buscarTodos() {
        List<Usuario> all = service.buscarTodosUsuarios();
        return ResponseEntity.status(HttpStatus.OK).body(all);
    }
}
