package br.com.fiap.restaurante.domain.interfaces.gateway;

import br.com.fiap.restaurante.domain.models.ItemCardapio;

import java.util.List;

public interface IItemCardapioGateway {

    ItemCardapio cadastrar(ItemCardapio itemCardapio);
    ItemCardapio atualizar(ItemCardapio itemCardapio);
    boolean deletar(Long itemCardapioId);

    List<ItemCardapio> buscarTodosItems(Long restauranteId);

    ItemCardapio buscarItemCardapioPorIdentificador(Long itemCardapioId);

    ItemCardapio buscarItemCardapioPorNome(String nomeItemCardapio);
}
