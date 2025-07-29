package br.com.fiap.restaurante.application.usecases.itemcardapio;

import br.com.fiap.restaurante.application.output.ItemCardapioOutput;
import br.com.fiap.restaurante.domain.interfaces.gateway.IItemCardapioGateway;
import br.com.fiap.restaurante.domain.models.ItemCardapio;

import java.util.List;

public class UseCaseAtualizarItemCardapio {

    private final IItemCardapioGateway gateway;

    private UseCaseAtualizarItemCardapio(IItemCardapioGateway gateway){this.gateway = gateway;}

    public static UseCaseAtualizarItemCardapio create(IItemCardapioGateway gateway){
        return new UseCaseAtualizarItemCardapio(gateway);
    }

    public List<ItemCardapioOutput> run (Long restauranteId) {

        List<ItemCardapio> itensCardapio = gateway.buscarTodosItems(restauranteId);

        return ItemCardapioOutput.fromDomain(itensCardapio);

    }

}
