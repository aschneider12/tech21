package br.com.fiap.restaurante.core.domain.usecases.itemcardapio;

import br.com.fiap.restaurante.core.interfaces.gateway.IItemCardapioGateway;

public class FactoryItemCardapioUseCase {

    protected final IItemCardapioGateway gateway;

    public FactoryItemCardapioUseCase(IItemCardapioGateway gateway) {
        this.gateway = gateway;
    }
}
