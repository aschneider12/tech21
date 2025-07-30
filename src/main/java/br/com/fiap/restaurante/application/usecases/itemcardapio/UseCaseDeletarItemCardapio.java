package br.com.fiap.restaurante.application.usecases.itemcardapio;

import br.com.fiap.restaurante.application.exceptions.ValidationException;
import br.com.fiap.restaurante.application.output.ItemCardapioOutput;
import br.com.fiap.restaurante.domain.interfaces.gateway.IItemCardapioGateway;
import br.com.fiap.restaurante.domain.models.ItemCardapio;
import br.com.fiap.restaurante.infra.database.entities.ItemCardapioEntity;

import java.util.List;
import java.util.Optional;

public class UseCaseDeletarItemCardapio {

    private final IItemCardapioGateway gateway;

    private UseCaseDeletarItemCardapio(IItemCardapioGateway gateway){this.gateway = gateway;}

    public static UseCaseDeletarItemCardapio create(IItemCardapioGateway gateway){
        return new UseCaseDeletarItemCardapio(gateway);
    }

    public boolean run (Long restauranteId, Long itemCardapioId) {

        ItemCardapio item = gateway.buscarItemCardapioPorIdentificador(itemCardapioId);

        if(item.getRestaurante().getId().equals(restauranteId)) {
            return gateway.deletar(item.getId());

        } else
            throw new ValidationException("Item não pertence ao restauranteId informado.");

    }

}
