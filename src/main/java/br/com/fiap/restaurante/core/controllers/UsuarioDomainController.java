package br.com.fiap.restaurante.core.controllers;

import br.com.fiap.restaurante.core.domain.usecases.usuario.UseCaseAdicionarPerfisUsuario;
import br.com.fiap.restaurante.core.domain.usecases.usuario.UseCaseBuscarPerfisUsuario;
import br.com.fiap.restaurante.core.domain.usecases.usuario.UseCaseCadastrarUsuario;
import br.com.fiap.restaurante.core.domain.usecases.usuario.UseCaseRemoverPerfisUsuario;
import br.com.fiap.restaurante.core.dtos.usuario.UsuarioInputDTO;
import br.com.fiap.restaurante.core.dtos.usuario.UsuarioOutputDTO;
import br.com.fiap.restaurante.core.exceptions.EntidadeJaExisteException;
import br.com.fiap.restaurante.core.gateways.UsuarioGateway;
import br.com.fiap.restaurante.core.interfaces.storage.IDataStorageUsuario;
import br.com.fiap.restaurante.core.mappers.UsuarioMapper;

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
public class UsuarioDomainController {

    private final IDataStorageUsuario dataSource;

    private UsuarioDomainController(IDataStorageUsuario iDataStorageUsuario){
        this.dataSource = iDataStorageUsuario;
    }

    public static UsuarioDomainController create(IDataStorageUsuario iDataStorageUsuario) {
        return new UsuarioDomainController(iDataStorageUsuario);
    }

    public UsuarioOutputDTO cadastrar(UsuarioInputDTO dto) {

        var gateway = UsuarioGateway.create(this.dataSource);
        var useCaseCadastrarUsuario = UseCaseCadastrarUsuario.create(gateway);

        try {

            var usuario = useCaseCadastrarUsuario.run(dto);
            // PODEMOS INCLUIR UM PRESENTER

           // return UsuarioOutputDTO.fromEntity(usuario);

            return null;

        } catch (EntidadeJaExisteException e) {
            System.err.println(e.getMessage());
            return null;  // deve retornar algo melhor, não suprimir o erro
        }
    }

     public List<String> listarPerfisUsuario(Long usuarioId){
         var gateway = UsuarioGateway.create(this.dataSource);
         var use = UseCaseBuscarPerfisUsuario.create(gateway);

         return use.run(usuarioId);
     }

    public void adicionarPerfis(Long usuarioId, List<String> perfisAdd) {
        var gateway = UsuarioGateway.create(this.dataSource);
        var use = UseCaseAdicionarPerfisUsuario.create(gateway);
        use.run(usuarioId, perfisAdd);
    }

    public void removerPerfis(Long usuarioId, List<String> perfisDel) {
        var gateway = UsuarioGateway.create(this.dataSource);
        var use = UseCaseRemoverPerfisUsuario.create(gateway);

        use.run(usuarioId, perfisDel);
    }

}
