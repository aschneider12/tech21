package br.com.fiap.restaurante.infra.doc;

import br.com.fiap.restaurante.infra.dtos.RestauranteDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Restaurantes", description = "Operações relacionadas ao CRUD dos restaurantes")
public interface RestauranteDocController {

    @Operation(summary = "Cadastrar novo restaurante", description = "Realiza o cadastro de um novo restaurante")
    ResponseEntity<RestauranteDTO> cadastrar(RestauranteDTO dto);

    @Operation(summary = "Atualizar existente", description = "Realiza a atualização da entidade existente")
    ResponseEntity<RestauranteDTO> atualizar(RestauranteDTO dto,  Long id);

    @Operation(summary = "Deletar por ID", description = "Realiza a exclusão da entidade existente")
    ResponseEntity<String> deletar(Long id);

    @Operation(summary = "Buscar por ID", description = "Buscar o cadastro da entidade por ID")
    ResponseEntity<RestauranteDTO> buscarPorId(Long id);

    @Operation(summary = "Buscar todos", description = "Busca o cadastro de todas entidades cadastradas")
    ResponseEntity<List<RestauranteDTO>> buscarTodos();
}
