package br.com.fiap.restaurante.core.domain.usecases.itemcardapio;

import br.com.fiap.restaurante.core.domain.entities.ItemCardapio;
import br.com.fiap.restaurante.core.gateways.ItemsCardapioGateway;
import br.com.fiap.restaurante.core.interfaces.gateway.IItemCardapioGateway;

import java.util.List;

public class UseCaseBuscarTodosItemCardapio {

    private final IItemCardapioGateway gateway;

    private UseCaseBuscarTodosItemCardapio(IItemCardapioGateway gateway){this.gateway = gateway;}

    public static UseCaseBuscarTodosItemCardapio create(IItemCardapioGateway gateway){
        return new UseCaseBuscarTodosItemCardapio(gateway);
    }

    public List<ItemCardapio> run () {

        List<ItemCardapio> itemCardapios = gateway.buscarTodosItems();

        return itemCardapios;
    }
}
