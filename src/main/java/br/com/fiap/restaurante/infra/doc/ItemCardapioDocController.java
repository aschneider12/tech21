package br.com.fiap.restaurante.infra.doc;

import br.com.fiap.restaurante.infra.dtos.ItemRequestDTO;
import br.com.fiap.restaurante.infra.dtos.ItemResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Restaurantes", description = "Operações relacionadas ao CRUD dos restaurantes")
public interface ItemCardapioDocController {

    @Operation(summary = "Adicionar novo item", description = "Realiza o cadastro de um novo item no cardápio do restaurante")
    public ResponseEntity<ItemResponseDTO> cadastrar(Long restauranteId, ItemRequestDTO dto);

    @Operation(summary = "Atualizar item cardápio", description = "Realiza a atualização de um item existente")
    public ResponseEntity<ItemResponseDTO> atualizar(Long restauranteId, Long idItem, ItemRequestDTO dto);

    @Operation(summary = "Deletar item", description = "Realiza a exclusão de um item do cardárpio do restaurante.")
    public ResponseEntity<Void> deletar(Long restauranteId, Long id);

    @Operation(summary = "Buscar item por ID", description = "Buscar o cadastro do item do cardápio por ID")
    ResponseEntity<ItemResponseDTO> buscarPorId(Long restauranteId, Long idItem);

    @Operation(summary = "Buscar todos itens do cardápio", description = "Busca o cadastro de todos os itens do restaurante")
    ResponseEntity<List<ItemResponseDTO>> buscarTodos(Long restauranteId);
}
