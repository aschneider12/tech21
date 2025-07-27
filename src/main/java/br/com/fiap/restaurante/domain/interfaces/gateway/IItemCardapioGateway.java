package br.com.fiap.restaurante.domain.interfaces.gateway;

import br.com.fiap.restaurante.domain.models.ItemCardapio;

import java.util.List;

public interface IItemCardapioGateway {

    ItemCardapio cadastrar(ItemCardapio itemCardapio);
    List<ItemCardapio> buscarTodosItems();
    ItemCardapio buscarItemCardapioPorIdentificador(Long id);
    ItemCardapio buscarItemCardapioPorNome(String nomeItem);
}
