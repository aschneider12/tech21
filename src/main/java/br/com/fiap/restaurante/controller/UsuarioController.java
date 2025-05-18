package br.com.fiap.restaurante.controller;

import br.com.fiap.restaurante.dtos.UsuarioRequestDTO;
import br.com.fiap.restaurante.dtos.UsuarioResponseDTO;
import br.com.fiap.restaurante.entities.Usuario;
import br.com.fiap.restaurante.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    UsuarioService service;

    //TODO - padronizar um meio de resposta aqui, enviando um objeto (status,msg,obj)

//    @PostMapping
//    @Operation(description = "Cadastrar um novo usuário.")
//    public ResponseEntity<Usuario> cadastrar(@RequestBody @Valid Usuario usuario) {
//        Usuario salvo = service.salvar(usuario);
//        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
//    }

    @PostMapping
    @Operation(description = "Cadastrar um novo usuário")
    public ResponseEntity<UsuarioResponseDTO> cadastrar(@Valid @RequestBody UsuarioRequestDTO usuarioDTO) {

        logger.info("FOI ACESSADO POST MAPPING ");


        Usuario usuario = service.converterParaEntidade(usuarioDTO);
    Usuario salvo = service.salvar(usuario);
    UsuarioResponseDTO responseDTO = service.converterParaDTO(salvo);
    return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);

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
}
