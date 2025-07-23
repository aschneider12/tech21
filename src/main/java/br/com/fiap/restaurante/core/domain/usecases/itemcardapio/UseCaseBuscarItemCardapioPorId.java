package br.com.fiap.restaurante.core.domain.usecases.itemcardapio;

import br.com.fiap.restaurante.core.domain.entities.ItemCardapio;
import br.com.fiap.restaurante.core.interfaces.gateway.IItemCardapioGateway;

public class UseCaseBuscarItemCardapioPorId {

    private final IItemCardapioGateway gateway;

    public UseCaseBuscarItemCardapioPorId(IItemCardapioGateway gateway) {
        this.gateway = gateway;
    }

    public ItemCardapio execute(Long id) {
        return gateway.buscarPorId(id);
    }
}
