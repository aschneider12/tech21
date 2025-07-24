package br.com.fiap.restaurante.core.presenters;

import br.com.fiap.restaurante.core.domain.entities.ItemCardapio;
import br.com.fiap.restaurante.core.dtos.itemcardapio.ItemCardapioOutputDTO;

///TODO validar restaurante, incluir nos testes
public class ItemCardapioPresenter {

    public static ItemCardapioOutputDTO toDTO(ItemCardapio itemCardapio) {

        return new ItemCardapioOutputDTO(
                itemCardapio.getId(),
                itemCardapio.getNome(),
                itemCardapio.getDescricao(),
                itemCardapio.getPreco(),
                itemCardapio.getTipoVenda(),
                null,//TODO
                itemCardapio.getPathFoto()


        );
    }
}
