package br.com.fiap.restaurante.core.domain.usecases.itemcardapio;

import br.com.fiap.restaurante.core.domain.entities.ItemCardapio;
import br.com.fiap.restaurante.core.interfaces.gateway.IItemCardapioGateway;

public class UseCaseCadastrarItemCardapio {
    private final IItemCardapioGateway gateway;
    public UseCaseCadastrarItemCardapio(IItemCardapioGateway gateway) {
        this.gateway = gateway;
    }
    public ItemCardapio execute(ItemCardapio item) {
        return gateway.cadastrar(item);
    }
}