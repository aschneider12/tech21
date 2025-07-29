package br.com.fiap.restaurante.application.usecases.itemcardapio;

import br.com.fiap.restaurante.application.output.ItemCardapioOutput;
import br.com.fiap.restaurante.domain.interfaces.gateway.IItemCardapioGateway;
import br.com.fiap.restaurante.domain.models.ItemCardapio;

import java.util.List;

public class UseCaseCadastrarItemCardapio {

    private final IItemCardapioGateway gateway;

    private UseCaseCadastrarItemCardapio(IItemCardapioGateway gateway){this.gateway = gateway;}

    public static UseCaseCadastrarItemCardapio create(IItemCardapioGateway gateway){
        return new UseCaseCadastrarItemCardapio(gateway);
    }

    public List<ItemCardapioOutput> run (Long restauranteId) {

        List<ItemCardapio> itensCardapio = gateway.buscarTodosItems(restauranteId);

        return ItemCardapioOutput.fromDomain(itensCardapio);

    }

}
