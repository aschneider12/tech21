package br.com.fiap.restaurante.application.controller;

import br.com.fiap.restaurante.application.service.PerfilService;
import br.com.fiap.restaurante.application.dtos.PerfilResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario/{usuarioId}/perfil")
public class PerfilController {

    private static final Logger logger = LoggerFactory.getLogger(PerfilController.class);

    @Autowired
    PerfilService service;

    @GetMapping
    public ResponseEntity<PerfilResponseDTO> listarPerfis(@PathVariable Long usuarioId) {

        PerfilResponseDTO dto = service.listarPerfis(usuarioId);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<Void> adicionarPerfis(
            @PathVariable Long usuarioId,
            @RequestBody PerfilRequestDTO dto) {

        service.adicionarPerfil(usuarioId, dto.perfis());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> removerPerfis(
            @PathVariable Long usuarioId,
            @RequestBody PerfilRequestDTO dto) {

        service.removerPerfil(usuarioId, dto.perfis());
        return ResponseEntity.noContent().build();
    }
}
