package br.com.fiap.restaurante.core.gateways;

import br.com.fiap.restaurante.core.domain.entities.ItemCardapio;
import br.com.fiap.restaurante.core.dtos.itemcardapio.ItemCardapioInputDTO;
import br.com.fiap.restaurante.core.dtos.itemcardapio.ItemCardapioOutputDTO;
import br.com.fiap.restaurante.core.interfaces.gateway.IItemCardapioGateway;
import br.com.fiap.restaurante.core.interfaces.storage.IDataStorageItemCardapio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ItemsCardapioGateway implements IItemCardapioGateway {

    private final IDataStorageItemCardapio dataStorageItemCardapio;

    private ItemsCardapioGateway(IDataStorageItemCardapio dataStorageItemCardapio) {
        this.dataStorageItemCardapio = dataStorageItemCardapio;
    }

    public static ItemsCardapioGateway create(IDataStorageItemCardapio dataStorageItemCardapio) {
        return new ItemsCardapioGateway(dataStorageItemCardapio);
    }

    @Override
    public ItemCardapio cadastrar(ItemCardapio itemCardapio) {

        ItemCardapioInputDTO dto = ItemCardapioInputDTO.fromEntity(itemCardapio);

        final ItemCardapioOutputDTO ic = this.dataStorageItemCardapio.cadastrar(dto);
        return itemCardapio.create(ic.nome(),ic.descricao(),ic.preco(),ic.tipoVenda());
    }

    @Override
    public List<ItemCardapio> buscarTodosItems() {

        List<ItemCardapioOutputDTO> retornoDTOs = dataStorageItemCardapio.buscarTodosItensCardapio();

        List<ItemCardapio> concreteItemCardapios = retornoDTOs.stream().map(dto
                    -> ItemCardapio.create(dto.id(),dto.nome(), dto.descricao(), dto.preco(), dto.tipoVenda()))
                    .collect(Collectors.toList());


        return concreteItemCardapios;
    }


    @Override
    public ItemCardapio buscarItemCardapioPorNome(String nomeItem) {

        ItemCardapioOutputDTO retornoDTO = dataStorageItemCardapio.buscarItemCardapioPorNome(nomeItem);

        return ItemCardapio.create(retornoDTO.id() ,retornoDTO.nome(), retornoDTO.descricao(), retornoDTO.preco(), retornoDTO.tipoVenda());

    }

    @Override
    public ItemCardapio buscarItemCardapioPorIdentificador(Long id) {

        ItemCardapioOutputDTO retornoDTO = dataStorageItemCardapio.buscarItemCardapioPorIdentificador(id);

        return ItemCardapio.create(retornoDTO.id() ,retornoDTO.nome(), retornoDTO.descricao(), retornoDTO.preco(), retornoDTO.tipoVenda());

    }

}

