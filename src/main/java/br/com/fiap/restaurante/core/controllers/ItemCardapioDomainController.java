package br.com.fiap.restaurante.core.controllers;

import br.com.fiap.restaurante.application.dtos.PerfilResponseDTO;
import br.com.fiap.restaurante.application.entities.TipoUsuario;
import br.com.fiap.restaurante.application.entities.Usuario;
import br.com.fiap.restaurante.application.entities.UsuarioPerfil;
import br.com.fiap.restaurante.application.exceptions.ValidationException;
import br.com.fiap.restaurante.core.interfaces.storage.IDataStorageItemCardapio;
import br.com.fiap.restaurante.core.interfaces.storage.IDataStorageUsuario;

import java.util.ArrayList;
import java.util.List;

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
