package br.com.fiap.restaurante.controller;

import br.com.fiap.restaurante.DTO.MudarSenhaDTO;
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
    public ResponseEntity<UsuarioResponseDTO> cadastrar(@RequestBody @Valid UsuarioRequestDTO usuarioDTO) {

        UsuarioResponseDTO responseDTO = service.cadastrar(usuarioDTO);

//        @Raquel o proprio @valid faz isso
//        if (usuarioDTO.tipoUsuario() == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tipo de usuário deve ser informado.");
//        }

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/{id}")
    @Operation(description = "Atualizar usuário existente.")

    public ResponseEntity<UsuarioResponseDTO> atualizar(@RequestBody @Valid UsuarioRequestDTO usuarioDTO,
                                                        @PathVariable(required = true) Long id) {

        UsuarioResponseDTO responseDTO  = service.atualizar(id, usuarioDTO);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Deletar usuário.")
    public ResponseEntity<?> deletar(@PathVariable(required = true) Long id) {
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
