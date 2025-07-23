package br.com.fiap.restaurante.core.interfaces.gateway;

import br.com.fiap.restaurante.core.domain.entities.ItemCardapio;
import br.com.fiap.restaurante.core.gateways.ItemsCardapioGateway;

import java.util.List;

public interface IItemCardapioGateway {

    ItemCardapio cadastrar(ItemCardapio itemCardapio);
    List<ItemCardapio> buscarTodosItems();
    ItemCardapio buscarItemCardapioPorIdentificador(Long id);
    ItemCardapio buscarItemCardapioPorNome(String nomeItem);
}
