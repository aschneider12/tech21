package br.com.fiap.restaurante.application.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ICrudRestController<?>  {

    @Operation(summary = "Cadastrar novo", description = "Realiza o cadastro de uma nova entidade")
    @PostMapping
    ResponseEntity<> cadastrar(@RequestBody @Valid  dto);

    @Operation(summary = "Atualizar existente", description = "Realiza a atualização da entidade existente")
    @PutMapping("/{id}")
    ResponseEntity<?> atualizar(@RequestBody @Valid <?> dto, @PathVariable(required = true) Long id);

    @Operation(summary = "Deletar por ID", description = "Realiza a exclusão da entidade existente")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletar(@PathVariable(required = true) Long id);

    @Operation(summary = "Buscar por ID", description = "Buscar o cadastro da entidade por ID")
    @GetMapping("/{id}")
    ResponseEntity<T> buscarPorId(@PathVariable(required = true) Long id);

    @Operation(summary = "Buscar todos", description = "Busca o cadastro de todas entidades cadastradas")
    @GetMapping
    ResponseEntity<List<T>> buscarTodos();

}
