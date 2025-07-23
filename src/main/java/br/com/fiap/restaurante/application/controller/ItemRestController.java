package br.com.fiap.restaurante.application.controller;

import br.com.fiap.restaurante.application.service.ItemService;
import br.com.fiap.restaurante.dtos.ItemRequestDTO;
import br.com.fiap.restaurante.dtos.ItemResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * A URI principal pertence ao restaurante
 * Daria para usar a seguinte URI
 *
 * < host >: /restaurante/<id>/itens-cardapio
 *
 *  uri api não segue os padrões dos outros controllers
 */
@RestController
@RequestMapping("/api/itens")
public class ItemRestController {

    private final ItemService service;

    public ItemRestController(ItemService service) {
        this.service = service;
    }

    @GetMapping
    public List<ItemResponseDTO> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ItemResponseDTO> criar(@RequestBody @Valid ItemRequestDTO dto) {
        return ResponseEntity.ok(service.criar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid ItemRequestDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
