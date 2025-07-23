package br.com.fiap.restaurante.core.gateways;

import br.com.fiap.restaurante.core.domain.entities.ItemCardapio;
import br.com.fiap.restaurante.core.dtos.itemcardapio.ItemCardapioInputDTO;
import br.com.fiap.restaurante.core.dtos.itemcardapio.ItemCardapioOutputDTO;
import br.com.fiap.restaurante.core.interfaces.gateway.IItemCardapioGateway;
import br.com.fiap.restaurante.core.interfaces.storage.IDataStorageItemCardapio;

import java.util.List;
import java.util.stream.Collectors;

public class ItemCardapioGateway implements IItemCardapioGateway {

    private final IDataStorageItemCardapio storage;

    public ItemCardapioGateway(IDataStorageItemCardapio storage) {
        this.storage = storage;
    }

    @Override
    public ItemCardapio cadastrar(ItemCardapio item) {
        ItemCardapioInputDTO dto = new ItemCardapioInputDTO(
            item.getNome(), item.getDescricao(), item.getPreco(), item.getTipoVenda(), item.getPathFoto()
        );

        ItemCardapioOutputDTO response = storage.cadastrar(dto);

        return ItemCardapio.create(
            response.id(), response.nome(), response.descricao(),
            response.preco(), response.tipoVenda(), response.pathFoto()
        );
    }

    @Override
    public List<ItemCardapio> buscarTodos() {
        return storage.buscarTodosItensCardapio()
            .stream()
            .map(dto -> ItemCardapio.create(
                dto.id(), dto.nome(), dto.descricao(), dto.preco(), dto.tipoVenda(), dto.pathFoto()))
            .collect(Collectors.toList());
    }

    @Override
    public ItemCardapio buscarPorNome(String nome) {
        var dto = storage.buscarItemCardapioPorNome(nome);
        return ItemCardapio.create(dto.id(), dto.nome(), dto.descricao(), dto.preco(), dto.tipoVenda(), dto.pathFoto());
    }

    @Override
    public ItemCardapio buscarPorId(Long id) {
        var dto = storage.buscarItemCardapioPorIdentificador(id);
        return ItemCardapio.create(dto.id(), dto.nome(), dto.descricao(), dto.preco(), dto.tipoVenda(), dto.pathFoto());
    }

    @Override
    public void deletar(Long id) {
        storage.deletar(id);
    }
}


