package br.com.fiap.restaurante.application.usecases.itemcardapio;

import br.com.fiap.restaurante.application.exceptions.EntidadeNaoEncontradaException;
import br.com.fiap.restaurante.application.exceptions.ValidationException;
import br.com.fiap.restaurante.application.input.ItemCardapioInput;
import br.com.fiap.restaurante.application.output.ItemCardapioOutput;
import br.com.fiap.restaurante.domain.interfaces.gateway.IItemCardapioGateway;

public class UseCaseAtualizarItemCardapio {

    private final IItemCardapioGateway gateway;

    private UseCaseAtualizarItemCardapio(IItemCardapioGateway gateway){this.gateway = gateway;}

    public static UseCaseAtualizarItemCardapio create(IItemCardapioGateway gateway){
        return new UseCaseAtualizarItemCardapio(gateway);
    }

    public ItemCardapioOutput run (Long itemCardapioId, ItemCardapioInput input) {

        var item = gateway.buscarItemCardapioPorIdentificador(itemCardapioId);
        if(item == null)
            throw new EntidadeNaoEncontradaException("Item Cardápio", "ID - "+itemCardapioId);

        if(!item.getRestaurante().getId().equals(input.restauranteId()))
            throw new ValidationException("Item não pertence ao restauranteId informado!");

        item = ItemCardapioInput.toDomain(input);

        item.setId(itemCardapioId);

        item = gateway.atualizar(item);

        return ItemCardapioOutput.fromDomain(item);
    }

}
