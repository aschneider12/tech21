package br.com.fiap.restaurante.application.controllers;

import br.com.fiap.restaurante.domain.domain.interfaces.storage.IDataStorageItemCardapio;

/**
 * DOMAIN CONTROLLER
 *
 * Porta de entrada para a application, todos devem instanciar um domain controller
 *
 * - Não confundir com o RestController
 * - Não teremos mais a camada service, já que está será executada pelos nossos use cases
 *
 */
public class ItemCardapioDomainController {

    private final IDataStorageItemCardapio dataSource;

    private ItemCardapioDomainController(IDataStorageItemCardapio iDataStorage){
        this.dataSource = iDataStorage;
    }

    public static ItemCardapioDomainController create(IDataStorageItemCardapio iDataStorage) {
        return new ItemCardapioDomainController(iDataStorage);
    }
//cria gateway e metodos necessarios para comunicação externa
}
