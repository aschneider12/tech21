package br.com.fiap.restaurante.application.controller;

import br.com.fiap.restaurante.application.dtos.PerfilRequestDTO;
import br.com.fiap.restaurante.application.dtos.PerfilResponseDTO;
import br.com.fiap.restaurante.application.repositories.adapter.UsuarioRepositoryAdapter;
import br.com.fiap.restaurante.application.repositories.jpa.UsuarioRepository;
import br.com.fiap.restaurante.core.controllers.UsuarioDomainController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario/{usuarioId}/perfil")
public class PerfilRestController {

    private static final Logger logger = LoggerFactory.getLogger(PerfilRestController.class);

    UsuarioDomainController domainController;

    public PerfilRestController(UsuarioRepository repository) {

        domainController = UsuarioDomainController.create(
                new UsuarioRepositoryAdapter(repository));
    }

    @GetMapping
    public ResponseEntity<PerfilResponseDTO> listarPerfis(@PathVariable Long usuarioId) {

      //  PerfilResponseDTO dto = service.listarPerfis(usuarioId);
       // to
        //domainController.listarPerfisUsuario(usuarioId);

        return ResponseEntity.ok(null);
    }

    @PostMapping
    public ResponseEntity<Void> adicionarPerfis(
            @PathVariable Long usuarioId,
            @RequestBody PerfilRequestDTO dto) {

       // service.adicionarPerfil(usuarioId, dto.perfis());
//        to domain controller
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> removerPerfis(
            @PathVariable Long usuarioId,
            @RequestBody PerfilRequestDTO dto) {

        //service.removerPerfil(usuarioId, dto.perfis());
//        to domain controller
        return ResponseEntity.noContent().build();
    }
}
