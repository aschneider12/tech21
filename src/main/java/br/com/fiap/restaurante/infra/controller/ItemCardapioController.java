package br.com.fiap.restaurante.infra.controller;

import br.com.fiap.restaurante.application.exceptions.ValidationException;
import br.com.fiap.restaurante.application.gateways.ItemCardapioGateway;
import br.com.fiap.restaurante.application.input.ItemCardapioInput;
import br.com.fiap.restaurante.application.output.ItemCardapioOutput;
import br.com.fiap.restaurante.application.usecases.itemcardapio.*;
import br.com.fiap.restaurante.infra.database.repositories.adapter.ItemCardapioRepositoryAdapter;
import br.com.fiap.restaurante.infra.database.repositories.jpa.ItemRepository;
import br.com.fiap.restaurante.infra.doc.ItemCardapioDocController;
import br.com.fiap.restaurante.infra.dtos.ItemRequestDTO;
import br.com.fiap.restaurante.infra.dtos.ItemResponseDTO;
import br.com.fiap.restaurante.infra.mappers.ItemCardapioDTOMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurante/{restauranteId}/cardapio")
public class ItemCardapioController implements ItemCardapioDocController {

    private final FactoryCardapioUseCase factoryCardapioUseCase;

    public ItemCardapioController(FactoryCardapioUseCase factory) {
        factoryCardapioUseCase = factory;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemResponseDTO> buscarPorId(@PathVariable Long restauranteId, @PathVariable Long id) {

        var uc = factoryCardapioUseCase.buscarItemCardapioPorID();
        ItemCardapioOutput output = uc.run(restauranteId, id);

        ItemResponseDTO dto = ItemCardapioDTOMapper.INSTANCE.toDTO(output);

        return ResponseEntity.ok(dto);
    }

    @GetMapping
    @Override
    public ResponseEntity<List<ItemResponseDTO>> buscarTodos(@PathVariable Long restauranteId) {

        var uc = factoryCardapioUseCase.buscarTodosItemCardapio();

        List<ItemCardapioOutput> allItensRestaurante = uc.run(restauranteId);

        List<ItemResponseDTO> outputDTOs = ItemCardapioDTOMapper.INSTANCE.toDTO(allItensRestaurante);

        return ResponseEntity.status(HttpStatus.OK).body(outputDTOs);
    }

    @PostMapping
    public ResponseEntity<ItemResponseDTO> cadastrar(@PathVariable Long restauranteId, @RequestBody @Valid  ItemRequestDTO dto) {

        dto.setRestauranteId(restauranteId);

        var uc = factoryCardapioUseCase.cadastrarItemCardapio();

        ItemCardapioInput input = ItemCardapioDTOMapper.INSTANCE.toInputApplication(dto);

        var cadastrado = uc.run(input);

        ItemResponseDTO saida = ItemCardapioDTOMapper.INSTANCE.toDTO(cadastrado);

        return ResponseEntity.ok(saida);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemResponseDTO> atualizar(@PathVariable Long restauranteId, @PathVariable Long id, @RequestBody @Valid ItemRequestDTO dto) {

        dto.setRestauranteId(restauranteId);

        var uc = factoryCardapioUseCase.atualizarItemCardapio();

        ItemCardapioInput input = ItemCardapioDTOMapper.INSTANCE.toInputApplication(dto);

       var atualizado = uc.run(id, input);

        return ResponseEntity.ok(ItemCardapioDTOMapper.INSTANCE.toDTO(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long restauranteId, @PathVariable Long id) {

        var uc = factoryCardapioUseCase.deletarItemCardapio();

        if(!uc.run(restauranteId, id))
            throw new ValidationException("Não foi possível apagar o item");

        return ResponseEntity.noContent().build();
    }
}
