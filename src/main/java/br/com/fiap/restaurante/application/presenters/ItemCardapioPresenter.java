package br.com.fiap.restaurante.application.presenters;

import br.com.fiap.restaurante.application.output.ItemCardapioOutput;
import br.com.fiap.restaurante.domain.models.ItemCardapio;

///TODO validar restaurante, incluir nos testes
public class ItemCardapioPresenter {

    public static ItemCardapioOutput toDTO(ItemCardapio itemCardapio) {

        return new ItemCardapioOutput(
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
