package br.com.fiap.restaurante.core.interfaces.storage;

import br.com.fiap.restaurante.core.dtos.itemcardapio.ItemCardapioInputDTO;
import br.com.fiap.restaurante.core.dtos.itemcardapio.ItemCardapioOutputDTO;

import java.util.List;

public interface IDataStorageItemCardapio {

    ItemCardapioOutputDTO cadastrar(ItemCardapioInputDTO dto);

    List<ItemCardapioOutputDTO> buscarTodosItensCardapio();

    ItemCardapioOutputDTO buscarItemCardapioPorNome(String nome);

    ItemCardapioOutputDTO buscarItemCardapioPorIdentificador(Long id);

    void deletar(Long id);
}

