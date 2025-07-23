package br.com.fiap.restaurante.application.controller;

import br.com.fiap.restaurante.application.mappers.ItemCardapioMapper;
import br.com.fiap.restaurante.core.dtos.itemcardapio.ItemCardapioInputDTO;
import br.com.fiap.restaurante.core.dtos.itemcardapio.ItemCardapioOutputDTO;
import br.com.fiap.restaurante.core.domain.usecases.itemcardapio.UseCaseCadastrarItemCardapio;
import br.com.fiap.restaurante.core.domain.usecases.itemcardapio.UseCaseBuscarTodosItemCardapio;
import br.com.fiap.restaurante.core.domain.usecases.itemcardapio.UseCaseBuscarItemCardapioPorId;
import br.com.fiap.restaurante.core.domain.usecases.itemcardapio.UseCaseDeletarItemCardapio;
import br.com.fiap.restaurante.core.interfaces.gateway.IItemCardapioGateway;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/itens")
public class ItemCardapioRestController {

    private final UseCaseCadastrarItemCardapio cadastrarUseCase;
    private final UseCaseBuscarTodosItemCardapio buscarTodosUseCase;
    private final UseCaseBuscarItemCardapioPorId buscarPorIdUseCase;
    private final UseCaseDeletarItemCardapio deletarUseCase;

    public ItemCardapioRestController(IItemCardapioGateway gateway) {
        this.cadastrarUseCase = new UseCaseCadastrarItemCardapio(gateway);
        this.buscarTodosUseCase = new UseCaseBuscarTodosItemCardapio(gateway);
        this.buscarPorIdUseCase = new UseCaseBuscarItemCardapioPorId(gateway);
        this.deletarUseCase = new UseCaseDeletarItemCardapio(gateway);
    }

    @PostMapping
    public ItemCardapioOutputDTO cadastrar(@RequestBody ItemCardapioInputDTO dto) {
        var entity = ItemCardapioMapper.toEntity(dto);
        return ItemCardapioMapper.toDTO(cadastrarUseCase.execute(entity));
    }

    @GetMapping
    public List<ItemCardapioOutputDTO> listar() {
        return buscarTodosUseCase.execute().stream()
            .map(ItemCardapioMapper::toDTO)
            .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ItemCardapioOutputDTO buscarPorId(@PathVariable Long id) {
        return ItemCardapioMapper.toDTO(buscarPorIdUseCase.execute(id));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        deletarUseCase.execute(id);
    }
}

