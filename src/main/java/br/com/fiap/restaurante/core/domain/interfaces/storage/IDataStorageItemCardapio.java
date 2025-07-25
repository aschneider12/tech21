package br.com.fiap.restaurante.core.domain.interfaces.storage;

import br.com.fiap.restaurante.application.dtos.itemcardapio.ItemCardapioInputDTO;
import br.com.fiap.restaurante.application.dtos.itemcardapio.ItemCardapioOutputDTO;

import java.util.List;

public interface IDataStorageItemCardapio {

    ItemCardapioOutputDTO cadastrar(ItemCardapioInputDTO dto);

    List<ItemCardapioOutputDTO> buscarTodosItensCardapio();

    ItemCardapioOutputDTO buscarItemCardapioPorIdentificador(Long id);
    ItemCardapioOutputDTO buscarItemCardapioPorNome(String nome);


}
