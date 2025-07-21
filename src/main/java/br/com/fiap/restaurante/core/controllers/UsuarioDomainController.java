package br.com.fiap.restaurante.core.controllers;

import br.com.fiap.restaurante.core.domain.usecases.usuario.UseCaseCadastrarUsuario;
import br.com.fiap.restaurante.core.dtos.usuario.UsuarioCadastroDTO;
import br.com.fiap.restaurante.core.dtos.usuario.UsuarioRetornoDTO;
import br.com.fiap.restaurante.core.exceptions.EntidadeJaExisteException;
import br.com.fiap.restaurante.core.gateways.UsuarioGateway;
import br.com.fiap.restaurante.core.interfaces.IDataStorageUsuario;

/**
 * DOMAIN CONTROLLER
 *
 * Porta de entrada para a application, todos devem instanciar um domain controller
 *
 * - Não confundir com o RestController
 * - Não teremos mais a camada service, já que está será executada pelos nossos use cases
 *
 */
public class UsuarioDomainController {

    private final IDataStorageUsuario dataSource;

    private UsuarioDomainController(IDataStorageUsuario iDataStorageUsuario){
        this.dataSource = iDataStorageUsuario;
    }

    public static UsuarioDomainController create(IDataStorageUsuario iDataStorageUsuario) {
        return new UsuarioDomainController(iDataStorageUsuario);
    }

    public UsuarioRetornoDTO cadastrar(UsuarioCadastroDTO dto) {

        var gateway = UsuarioGateway.create(this.dataSource);
        var useCaseCadastrarUsuario = UseCaseCadastrarUsuario.create(gateway);

        try {

            var usuario = useCaseCadastrarUsuario.run(dto);
            // PODEMOS INCLUIR UM PRESENTER
            return UsuarioRetornoDTO.fromEntity(usuario);

        } catch (EntidadeJaExisteException e) {
            System.err.println(e.getMessage());
            return null;  // deve retornar algo melhor, não suprimir o erro
        }
    }

}
