package br.com.fiap.restaurante.core.presenters;

import br.com.fiap.restaurante.core.domain.entities.ItemCardapio;
import br.com.fiap.restaurante.core.dtos.itemcardapio.ItemCardapioOutputDTO;

public class ItemCardapioPresenter {

    public static ItemCardapioOutputDTO toDTO(ItemCardapio itemCardapio) {

        return new ItemCardapioOutputDTO(
                itemCardapio.getId(),
                itemCardapio.getNome(),
                itemCardapio.getDescricao(),
                itemCardapio.getPreco(),
                itemCardapio.getTipoVenda(),
                itemCardapio.getPathFoto()


        );
    }
}
