package br.com.fiap.restaurante.core.interfaces.storage;

import br.com.fiap.restaurante.core.dtos.itemcardapio.ItemCardapioInputDTO;
import br.com.fiap.restaurante.core.dtos.itemcardapio.ItemCardapioOutputDTO;

import java.util.List;

public interface IDataStorageItemCardapio {

    ItemCardapioOutputDTO cadastrar(ItemCardapioInputDTO dto);

    List<ItemCardapioOutputDTO> buscarTodosItensCardapio();

    ItemCardapioOutputDTO buscarItemCardapioPorIdentificador(Long id);
    ItemCardapioOutputDTO buscarItemCardapioPorNome(String nome);


}
