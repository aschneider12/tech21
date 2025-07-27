package br.com.fiap.restaurante.domain.interfaces.storage;

import br.com.fiap.restaurante.domain.models.ItemCardapio;

import java.util.List;

public interface IDataStorageItemCardapio {

    ItemCardapio cadastrar(ItemCardapio itemCardapio);

    List<ItemCardapio> buscarTodosItensCardapio();

    ItemCardapio buscarItemCardapioPorIdentificador(Long id);
    ItemCardapio buscarItemCardapioPorNome(String nome);


}
