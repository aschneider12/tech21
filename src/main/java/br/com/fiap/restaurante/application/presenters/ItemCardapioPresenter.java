package br.com.fiap.restaurante.application.presenters;

import br.com.fiap.restaurante.domain.domain.models.ItemCardapio;
import br.com.fiap.restaurante.application.dtos.itemcardapio.ItemCardapioOutputDTO;

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
