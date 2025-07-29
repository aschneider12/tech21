package br.com.fiap.restaurante.application.usecases.itemcardapio;

import br.com.fiap.restaurante.application.output.ItemCardapioOutput;
import br.com.fiap.restaurante.domain.models.ItemCardapio;
import br.com.fiap.restaurante.domain.interfaces.gateway.IItemCardapioGateway;

import java.util.List;

public class UseCaseBuscarTodosItemCardapio {

    private final IItemCardapioGateway gateway;

    private UseCaseBuscarTodosItemCardapio(IItemCardapioGateway gateway){this.gateway = gateway;}

    public static UseCaseBuscarTodosItemCardapio create(IItemCardapioGateway gateway){
        return new UseCaseBuscarTodosItemCardapio(gateway);
    }

    public List<ItemCardapioOutput> run (Long restauranteId) {

        List<ItemCardapio> itensCardapio = gateway.buscarTodosItems(restauranteId);

        return ItemCardapioOutput.fromDomain(itensCardapio);

    }
}
