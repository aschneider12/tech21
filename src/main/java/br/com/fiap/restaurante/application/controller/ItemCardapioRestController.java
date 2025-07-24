package br.com.fiap.restaurante.application.controller;

import br.com.fiap.restaurante.core.domain.entities.ItemCardapio;
import br.com.fiap.restaurante.core.domain.usecases.itemcardapio.*;
import br.com.fiap.restaurante.core.dtos.itemcardapio.ItemCardapioInputDTO;
import br.com.fiap.restaurante.core.dtos.itemcardapio.ItemCardapioOutputDTO;
import br.com.fiap.restaurante.core.presenters.ItemCardapioPresenter;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/itens-cardapio")
public class ItemCardapioRestController {

    private final UseCaseCadastrarItemCardapio cadastrarUseCase;
    private final UseCaseBuscarTodosItemCardapio buscarTodosUseCase;
    private final UseCaseBuscarItemCardapioPorId buscarPorIdUseCase;
    private final UseCaseDeletarItemCardapio deletarUseCase;

    public ItemCardapioRestController(
        UseCaseCadastrarItemCardapio cadastrarUseCase,
        UseCaseBuscarTodosItemCardapio buscarTodosUseCase,
        UseCaseBuscarItemCardapioPorId buscarPorIdUseCase,
        UseCaseDeletarItemCardapio deletarUseCase
    ) {
        this.cadastrarUseCase = cadastrarUseCase;
        this.buscarTodosUseCase = buscarTodosUseCase;
        this.buscarPorIdUseCase = buscarPorIdUseCase;
        this.deletarUseCase = deletarUseCase;
    }

    @PostMapping
    public ItemCardapioOutputDTO cadastrar(@RequestBody ItemCardapioInputDTO dto) {
        ItemCardapio item = ItemCardapio.create(dto.nome(), dto.descricao(), dto.preco(), dto.tipoVenda(), dto.pathFoto());
        return ItemCardapioPresenter.toDTO(cadastrarUseCase.execute(item));
    }

    @GetMapping
    public List<ItemCardapioOutputDTO> buscarTodos() {
        return buscarTodosUseCase.execute().stream()
                .map(ItemCardapioPresenter::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ItemCardapioOutputDTO buscarPorId(@PathVariable Long id) {
        return ItemCardapioPresenter.toDTO(buscarPorIdUseCase.execute(id));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        deletarUseCase.execute(id);
    }
}

