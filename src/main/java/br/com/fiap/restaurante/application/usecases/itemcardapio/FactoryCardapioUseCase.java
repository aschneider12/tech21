package br.com.fiap.restaurante.application.usecases.itemcardapio;

import br.com.fiap.restaurante.domain.interfaces.gateway.IItemCardapioGateway;

public class FactoryCardapioUseCase {

    protected final IItemCardapioGateway gateway;

    public FactoryCardapioUseCase(IItemCardapioGateway gateway) {
        this.gateway = gateway;
    }

    public UseCaseAtualizarItemCardapio atualizarItemCardapio() {
        return UseCaseAtualizarItemCardapio.create(gateway);
    }

    public UseCaseBuscarItemCardapioPorID buscarItemCardapioPorID() {
        return UseCaseBuscarItemCardapioPorID.create(gateway);
    }

    public UseCaseBuscarTodosItemCardapio buscarTodosItemCardapio() {
        return UseCaseBuscarTodosItemCardapio.create(gateway);
    }

    public UseCaseCadastrarItemCardapio cadastrarItemCardapio(){
        return UseCaseCadastrarItemCardapio.create(gateway);
    }

    public UseCaseDeletarItemCardapio deletarItemCardapio(){
        return UseCaseDeletarItemCardapio.create(gateway);
    }
}
