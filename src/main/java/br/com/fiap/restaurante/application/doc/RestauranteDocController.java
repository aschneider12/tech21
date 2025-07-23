package br.com.fiap.restaurante.application.doc;
import br.com.fiap.restaurante.application.dtos.RestauranteInsertDTO;
import br.com.fiap.restaurante.application.dtos.RestauranteResponseDTO;
import br.com.fiap.restaurante.application.dtos.RestauranteUpdateDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Restaurantes", description = "Operações relacionadas ao CRUD dos restaurantes")
public interface RestauranteDocController {

    @Operation(summary = "Cadastrar novo restaurante", description = "Realiza o cadastro de um novo restaurante")
    ResponseEntity<RestauranteResponseDTO> cadastrar(RestauranteInsertDTO dto);

    @Operation(summary = "Atualizar existente", description = "Realiza a atualização da entidade existente")
    ResponseEntity<RestauranteResponseDTO> atualizar(RestauranteUpdateDTO dto,  Long id);

    @Operation(summary = "Deletar por ID", description = "Realiza a exclusão da entidade existente")
    ResponseEntity<String> deletar(Long id);

    @Operation(summary = "Buscar por ID", description = "Buscar o cadastro da entidade por ID")
    ResponseEntity<RestauranteResponseDTO> buscarPorId(Long id);

    @Operation(summary = "Buscar todos", description = "Busca o cadastro de todas entidades cadastradas")
    ResponseEntity<List<RestauranteResponseDTO>> buscarTodos();
}
