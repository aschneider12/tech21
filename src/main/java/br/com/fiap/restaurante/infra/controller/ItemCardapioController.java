package br.com.fiap.restaurante.infra.controller;

import br.com.fiap.restaurante.application.gateways.ItemCardapioGateway;
import br.com.fiap.restaurante.application.output.ItemCardapioOutput;
import br.com.fiap.restaurante.application.usecases.itemcardapio.UseCaseBuscarItemCardapioPorID;
import br.com.fiap.restaurante.application.usecases.itemcardapio.UseCaseBuscarTodosItemCardapio;
import br.com.fiap.restaurante.application.usecases.itemcardapio.UseCaseDeletarItemCardapio;
import br.com.fiap.restaurante.domain.models.ItemCardapio;
import br.com.fiap.restaurante.infra.database.mappers.ItemCardapioEntityMapper;
import br.com.fiap.restaurante.infra.database.repositories.adapter.ItemCardapioRepositoryAdapter;
import br.com.fiap.restaurante.infra.database.repositories.jpa.ItemRepository;
import br.com.fiap.restaurante.infra.doc.ItemCardapioDocController;
import br.com.fiap.restaurante.infra.dtos.ItemRequestDTO;
import br.com.fiap.restaurante.infra.dtos.ItemResponseDTO;
import br.com.fiap.restaurante.infra.dtos.RestauranteDTO;
import br.com.fiap.restaurante.infra.mappers.ItemCardapioDTOMapper;
import br.com.fiap.restaurante.infra.mappers.RestauranteDTOMapper;
import br.com.fiap.restaurante.infra.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurante/{restauranteId}/cardapio")
public class ItemCardapioController implements ItemCardapioDocController {

    private final ItemCardapioGateway gateway;

    private final ItemService service;

    public ItemCardapioController(ItemRepository repository, ItemService service) {
        gateway = ItemCardapioGateway.create(new ItemCardapioRepositoryAdapter(repository));
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemResponseDTO> buscarPorId(@PathVariable Long restauranteId, @PathVariable Long id) {

        var uc = UseCaseBuscarItemCardapioPorID.create(gateway);
        ItemCardapioOutput output = uc.run(restauranteId, id);

        ItemResponseDTO dto = ItemCardapioDTOMapper.INSTANCE.toDTO(output);

        return ResponseEntity.ok(dto);
    }

    @GetMapping
    @Override
    public ResponseEntity<List<ItemResponseDTO>> buscarTodos(@PathVariable Long restauranteId) {

        var uc = UseCaseBuscarTodosItemCardapio.create(gateway);

        List<ItemCardapioOutput> allItensRestaurante = uc.run(restauranteId);

        List<ItemResponseDTO> outputDTOs = ItemCardapioDTOMapper.INSTANCE.toDTO(allItensRestaurante);

        return ResponseEntity.status(HttpStatus.OK).body(outputDTOs);
    }

    @PostMapping
    public ResponseEntity<ItemResponseDTO> cadastrar(@PathVariable Long restauranteId, @RequestBody @Valid ItemRequestDTO dto) {
        return ResponseEntity.ok(service.criar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemResponseDTO> atualizar(@PathVariable Long restauranteId, @PathVariable Long id, @RequestBody @Valid ItemRequestDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long restauranteId, @PathVariable Long id) {

        var uc = UseCaseDeletarItemCardapio.create(gateway);

        if(uc.run(restauranteId, id))

        if(service.deletar(id))

        return ResponseEntity.noContent().build();
    }
}
