package br.com.fiap.restaurante.application.controller;

import br.com.fiap.restaurante.application.doc.PerfilDocController;
import br.com.fiap.restaurante.application.dtos.PerfilRequestDTO;
import br.com.fiap.restaurante.application.dtos.PerfilResponseDTO;
import br.com.fiap.restaurante.application.entities.TipoUsuario;
import br.com.fiap.restaurante.application.repositories.adapter.UsuarioRepositoryAdapter;
import br.com.fiap.restaurante.application.repositories.jpa.UsuarioRepository;
import br.com.fiap.restaurante.core.controllers.UsuarioDomainController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuario/{usuarioId}/perfil")
public class PerfilRestController implements PerfilDocController {

    private static final Logger logger = LoggerFactory.getLogger(PerfilRestController.class);

    UsuarioDomainController domainController;

    public PerfilRestController(UsuarioRepository repository) {

        domainController = UsuarioDomainController.create(
                new UsuarioRepositoryAdapter(repository));
    }

    @Override
    @GetMapping
    public ResponseEntity<PerfilResponseDTO> listarPerfis(@PathVariable Long usuarioId) {

        List<String> perfis = domainController.listarPerfisUsuario(usuarioId);

        List<TipoUsuario> perfisEnum = perfis.stream().map(TipoUsuario::valueOf).toList();


        //  PerfilResponseDTO dto = service.listarPerfis(usuarioId);

       // List<String> perfis = domainController.listarPerfisUsuario(usuarioId);

        return ResponseEntity.ok(new PerfilResponseDTO("usuario nome", perfisEnum));
    }
    @Override
    @PostMapping
    public ResponseEntity<Void> adicionarPerfis(
            @PathVariable Long usuarioId,
            @RequestBody PerfilRequestDTO dto) {

        List<String> perfisString = dto.perfis().stream().map(Enum::name).toList();

        domainController.adicionarPerfis(usuarioId, perfisString);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Void> removerPerfis(
            @PathVariable Long usuarioId,
            @RequestBody PerfilRequestDTO dto) {

        List<String> perfisDel = dto.perfis().stream().map(Enum::name).toList();
        domainController.removerPerfis(usuarioId, perfisDel);

        return ResponseEntity.noContent().build();
    }
}
