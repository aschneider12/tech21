package br.com.fiap.restaurante.application.gateways;

import br.com.fiap.restaurante.application.dtos.itemcardapio.ItemCardapioInputDTO;
import br.com.fiap.restaurante.application.dtos.itemcardapio.ItemCardapioOutputDTO;
import br.com.fiap.restaurante.core.domain.interfaces.gateway.IItemCardapioGateway;
import br.com.fiap.restaurante.domain.domain.interfaces.storage.IDataStorageItemCardapio;
import br.com.fiap.restaurante.domain.domain.models.ItemCardapio;
import br.com.fiap.restaurante.infra.controller.mappers.ItemCardapioMapper;

import java.util.List;

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

        final ItemCardapioOutputDTO dtpoCadastrado= this.dataStorageItemCardapio.cadastrar(dto);

        return ItemCardapioMapper.toDomain(dtpoCadastrado);

//        return itemCardapio.create(ic.nome(),ic.descricao(),ic.preco(),ic.tipoVenda());
    }

    @Override
    public List<ItemCardapio> buscarTodosItems() {

        List<ItemCardapioOutputDTO> retornoDTOs = dataStorageItemCardapio.buscarTodosItensCardapio();

        return ItemCardapioMapper.toDomain(retornoDTOs);

//        List<ItemCardapio> concreteItemCardapios = retornoDTOs.stream().map(dto
//                    -> ItemCardapio.create(dto.id(),dto.nome(), dto.descricao(), dto.preco(), dto.tipoVenda()))
//                    .collect(Collectors.toList());


//        return concreteItemCardapios;
    }


    @Override
    public ItemCardapio buscarItemCardapioPorNome(String nomeItem) {

        ItemCardapioOutputDTO retornoDTO = dataStorageItemCardapio.buscarItemCardapioPorNome(nomeItem);

        return ItemCardapioMapper.toDomain(retornoDTO);

        //.create(retornoDTO.id() ,retornoDTO.nome(), retornoDTO.descricao(), retornoDTO.preco(), retornoDTO.tipoVenda());

    }

    @Override
    public ItemCardapio buscarItemCardapioPorIdentificador(Long id) {

        ItemCardapioOutputDTO retornoDTO = dataStorageItemCardapio.buscarItemCardapioPorIdentificador(id);
        return ItemCardapioMapper.toDomain(retornoDTO);

       // return ItemCardapio.create(retornoDTO.id() ,retornoDTO.nome(), retornoDTO.descricao(), retornoDTO.preco(), retornoDTO.tipoVenda());

    }

}

