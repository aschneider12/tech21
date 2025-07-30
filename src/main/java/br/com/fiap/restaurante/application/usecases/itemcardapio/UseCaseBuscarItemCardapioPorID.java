package br.com.fiap.restaurante.application.usecases.itemcardapio;

import br.com.fiap.restaurante.application.exceptions.ValidationException;
import br.com.fiap.restaurante.application.output.ItemCardapioOutput;
import br.com.fiap.restaurante.domain.interfaces.gateway.IItemCardapioGateway;
import br.com.fiap.restaurante.domain.models.ItemCardapio;

import java.util.List;

public class UseCaseBuscarItemCardapioPorID {

    private final IItemCardapioGateway gateway;

    private UseCaseBuscarItemCardapioPorID(IItemCardapioGateway gateway){this.gateway = gateway;}

    public static UseCaseBuscarItemCardapioPorID create(IItemCardapioGateway gateway){
        return new UseCaseBuscarItemCardapioPorID(gateway);
    }

    public ItemCardapioOutput run (Long restauranteId, Long itemCardapioId) {

        ItemCardapio item = gateway.buscarItemCardapioPorIdentificador(itemCardapioId);
        if(!item.getRestaurante().getId().equals(restauranteId))
            throw new ValidationException("Item não pertence ao restauranteId informado!");

        return ItemCardapioOutput.fromDomain(item);
    }
}
