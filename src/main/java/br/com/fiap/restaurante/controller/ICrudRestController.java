package br.com.fiap.restaurante.controller;

import br.com.fiap.restaurante.dtos.RestauranteInsertDTO;
import br.com.fiap.restaurante.dtos.RestauranteResponseDTO;
import br.com.fiap.restaurante.dtos.RestauranteUpdateDTO;
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


    ResponseEntity<RestauranteResponseDTO> cadastrar(@RequestBody @Valid RestauranteInsertDTO dto);

    @Operation(summary = "Atualizar existente", description = "Realiza a atualização da entidade existente")
    @PutMapping("/{id}")
    ResponseEntity<RestauranteResponseDTO> atualizar(@RequestBody @Valid RestauranteUpdateDTO dto, @PathVariable(required = true) Long id);

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
