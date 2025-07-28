package br.com.fiap.restaurante.infra.controller;

import br.com.fiap.restaurante.application.gateways.UsuarioGateway;
import br.com.fiap.restaurante.application.input.UsuarioInput;
import br.com.fiap.restaurante.application.output.UsuarioOutput;
import br.com.fiap.restaurante.application.usecases.usuario.*;
import br.com.fiap.restaurante.application.usecases.usuario.senha.UseCaseAlterarSenhaUsuario;
import br.com.fiap.restaurante.infra.database.repositories.adapter.UsuarioRepositoryAdapter;
import br.com.fiap.restaurante.infra.database.repositories.jpa.UsuarioRepository;
import br.com.fiap.restaurante.infra.doc.UsuarioDocController;
import br.com.fiap.restaurante.infra.dtos.*;
import br.com.fiap.restaurante.infra.mappers.UsuarioDTOMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController implements UsuarioDocController {

    private PasswordEncoder passwordEncoder;
    private final UsuarioGateway gateway;

    public UsuarioController(UsuarioRepository repository, PasswordEncoder passwordEncoder) {
        this.gateway = UsuarioGateway.create(new UsuarioRepositoryAdapter(repository));
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    @Override
    public ResponseEntity<UsuarioResponseDTO> cadastrar(@RequestBody @Valid UsuarioInsertDTO usuarioDTO) {

        UsuarioInput input = UsuarioDTOMapper.INSTANCE.toInputApplication(usuarioDTO);

        UseCaseCadastrarUsuario uc = UseCaseCadastrarUsuario.create(gateway);
        UsuarioOutput output = uc.run(input);

        UsuarioResponseDTO outputDTO = UsuarioDTOMapper.INSTANCE.toResponse(output);

        return ResponseEntity.status(HttpStatus.CREATED).body(outputDTO);

    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<UsuarioResponseDTO> atualizar(@RequestBody @Valid UsuarioUpdateDTO usuarioDTO,
                                                        @PathVariable(required = true) Long id) {

        UsuarioInput input = UsuarioDTOMapper.INSTANCE.toInputApplication(usuarioDTO);

        UseCaseAtualizarUsuario uc = UseCaseAtualizarUsuario.create(gateway);
        UsuarioOutput output = uc.run(id, input);

        UsuarioResponseDTO outputDTO = UsuarioDTOMapper.INSTANCE.toResponse(output);

        return ResponseEntity.status(HttpStatus.OK).body(outputDTO);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<String> deletar(@PathVariable(required = true) Long id) {

        var uc = UseCaseDeletarUsuario.create(gateway);

        uc.run(id);

        return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado!");
    }

    @GetMapping
    @Override
    public ResponseEntity<List<UsuarioResponseDTO>> buscarTodos() {

        var uc =  UseCaseBuscarTodosUsuarios.create(gateway);

        List<UsuarioResponseDTO> response = UsuarioDTOMapper.INSTANCE.toResponse(uc.run());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable(required = true) Long id) {

        var uc = UseCaseBuscarUsuarioPorID.create(gateway);
        UsuarioOutput output = uc.run(id);

        UsuarioResponseDTO dtoResponse = UsuarioDTOMapper.INSTANCE.toResponse(output);

        return ResponseEntity.status(HttpStatus.OK).body(dtoResponse);
    }

    @PatchMapping("/mudar-senha/{id}")
    @Override
    public ResponseEntity<Void> mudarSenha(@RequestBody(required = true) MudarSenhaDTO mudarSenhaDTO, @PathVariable Long id) {

        var uc = UseCaseAlterarSenhaUsuario.create(gateway, passwordEncoder);

        uc.run(id, mudarSenhaDTO.senhaAntiga(), mudarSenhaDTO.senhaNova());

        return ResponseEntity.noContent().build();
    }
}
