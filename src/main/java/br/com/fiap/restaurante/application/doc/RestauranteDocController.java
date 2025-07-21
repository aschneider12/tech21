package br.com.fiap.restaurante.application.doc;

import br.com.fiap.restaurante.core.dtos.restaurante.RestauranteCadastroDTO;
import br.com.fiap.restaurante.core.dtos.restaurante.RestauranteRetornoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Restaurantes", description = "Operações relacionadas a restaurantes")
public interface RestauranteDocController {

    @Operation(summary = "Cadastrar novo", description = "Realiza o cadastro de uma nova entidade")
    ResponseEntity<RestauranteRetornoDTO> cadastrar(@RequestBody @Valid RestauranteCadastroDTO dto);

    @Operation(summary = "Atualizar existente", description = "Realiza a atualização da entidade existente")
    ResponseEntity<RestauranteRetornoDTO> atualizar(@RequestBody @Valid RestauranteCadastroDTO dto, @PathVariable(required = true) Long id);

    @Operation(summary = "Deletar por ID", description = "Realiza a exclusão da entidade existente")
    ResponseEntity<String> deletar(@PathVariable(required = true) Long id);

    @Operation(summary = "Buscar por ID", description = "Buscar o cadastro da entidade por ID")
    ResponseEntity<RestauranteRetornoDTO> buscarPorId(@PathVariable(required = true) Long id);

    @Operation(summary = "Buscar todos", description = "Busca o cadastro de todas entidades cadastradas")
    ResponseEntity<List<RestauranteRetornoDTO>> buscarTodos();
}
