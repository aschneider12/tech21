package br.com.fiap.restaurante.core.domain.usecases.itemcardapio;

import br.com.fiap.restaurante.core.gateways.ItemsCardapioGateway;

public class FactoryItemCardapioUseCase {

    protected  final ItemsCardapioGateway gateway;

    public FactoryItemCardapioUseCase(ItemsCardapioGateway gateway) { this.gateway = gateway; }



}
