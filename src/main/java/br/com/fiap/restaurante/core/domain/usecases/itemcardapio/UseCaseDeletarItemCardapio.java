package br.com.fiap.restaurante.core.domain.usecases.itemcardapio;

import br.com.fiap.restaurante.core.interfaces.gateway.IItemCardapioGateway;

public class UseCaseDeletarItemCardapio {
    private final IItemCardapioGateway gateway;
    public UseCaseDeletarItemCardapio(IItemCardapioGateway gateway) {
        this.gateway = gateway;
    }
    public void execute(Long id) {
        gateway.deletar(id);
    }
}
