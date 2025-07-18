package br.com.fiap.restaurante.controller;

import br.com.fiap.restaurante.entities.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public interface ICrudRestController<T, ID> {

    @Operation(summary = "Cadastrar novo", description = "Realiza o cadastro de uma nova entidade")
    @PostMapping
    ResponseEntity<T> cadastrar(@RequestBody @Valid T dto);

    @Operation(summary = "Atualizar existente", description = "Realiza a atualização da entidade existente")
    @PutMapping("/{id}")
    ResponseEntity<T> atualizar(@RequestBody @Valid T dto, @PathVariable(required = true) ID id);

    @Operation(summary = "Deletar por ID", description = "Realiza a exclusão da entidade existente")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletar(@PathVariable(required = true) ID id);

    @Operation(summary = "Buscar por ID", description = "Buscar o cadastro da entidade por ID")
    @GetMapping("/{id}")
    ResponseEntity<T> buscarPorId(@PathVariable(required = true) ID id);

    @Operation(summary = "Buscar todos", description = "Busca o cadastro de todas entidades cadastradas")
    @GetMapping
    ResponseEntity<List<T>> buscarTodos();

}
