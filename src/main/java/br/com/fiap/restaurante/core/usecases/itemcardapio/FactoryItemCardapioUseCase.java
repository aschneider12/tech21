package br.com.fiap.restaurante.core.usecases.itemcardapio;

import br.com.fiap.restaurante.application.gateways.ItemsCardapioGateway;

public class FactoryItemCardapioUseCase {

    protected  final ItemsCardapioGateway gateway;

    public FactoryItemCardapioUseCase(ItemsCardapioGateway gateway) { this.gateway = gateway; }



}
