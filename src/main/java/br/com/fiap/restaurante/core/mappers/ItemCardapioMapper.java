package br.com.fiap.restaurante.core.mappers;

import br.com.fiap.restaurante.core.domain.entities.ItemCardapio;
import br.com.fiap.restaurante.core.dtos.itemcardapio.ItemCardapioOutputDTO;

import java.util.List;

public class ItemCardapioMapper {

    public static ItemCardapioOutputDTO toOutput(ItemCardapio itemCardapio){

        if(itemCardapio == null) return null;

        return ItemCardapioOutputDTO.create(
                itemCardapio.getId(),
                itemCardapio.getNome(),
                itemCardapio.getDescricao(),
                itemCardapio.getPreco(),
                itemCardapio.getTipoVenda(),
                //parar de mapear os objetos abaixo, se houverem
                RestauranteMapper.toOutputWithoutRelationship(itemCardapio.getRestaurante()),
                itemCardapio.getPathFoto()
        );
    }

    public static List<ItemCardapioOutputDTO> toOutput(List<ItemCardapio> itensCardapio){

        return itensCardapio.stream().map(ItemCardapioMapper::toOutput).toList();
    }

}
