package br.com.fiap.restaurante.application.usecases.itemcardapio;

import br.com.fiap.restaurante.application.exceptions.EntidadeJaExisteException;
import br.com.fiap.restaurante.application.input.ItemCardapioInput;
import br.com.fiap.restaurante.application.output.ItemCardapioOutput;
import br.com.fiap.restaurante.domain.interfaces.gateway.IItemCardapioGateway;
import br.com.fiap.restaurante.domain.models.ItemCardapio;

public class UseCaseCadastrarItemCardapio {

    private final IItemCardapioGateway gateway;

    private UseCaseCadastrarItemCardapio(IItemCardapioGateway gateway){this.gateway = gateway;}

    public static UseCaseCadastrarItemCardapio create(IItemCardapioGateway gateway){
        return new UseCaseCadastrarItemCardapio(gateway);
    }

    public ItemCardapioOutput run (ItemCardapioInput input) {

        ItemCardapio itemCardapio = ItemCardapioInput.toDomain(input);

       var existente = gateway.buscarItemCardapioPorNome(itemCardapio.getNome());
       if(existente != null)
           throw new EntidadeJaExisteException("Item Cardápio", itemCardapio.getNome());

        var cadastrado = gateway.cadastrar(itemCardapio);

        return ItemCardapioOutput.fromDomain(cadastrado);
    }
}
