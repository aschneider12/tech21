package br.com.fiap.restaurante.domain.interfaces.storage;

import br.com.fiap.restaurante.domain.models.ItemCardapio;

import java.util.List;

public interface IDataStorageItemCardapio {

    ItemCardapio cadastrar(ItemCardapio itemCardapio);

    ItemCardapio atualizar(ItemCardapio itemCardapio);

    boolean deletar(Long itemCardapioId);

    List<ItemCardapio> buscarTodosItensCardapio(Long restauranteId);

    ItemCardapio buscarItemCardapioPorIdentificador(Long itemCardapioId);

    ItemCardapio buscarItemCardapioPorNome(String nome);


}
