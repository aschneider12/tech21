package br.com.fiap.restaurante.core.gateways;

import br.com.fiap.restaurante.core.domain.entities.Usuario;
import br.com.fiap.restaurante.core.dtos.usuario.UsuarioInputDTO;
import br.com.fiap.restaurante.core.dtos.usuario.UsuarioOutputDTO;
import br.com.fiap.restaurante.core.interfaces.storage.IDataStorageUsuario;
import br.com.fiap.restaurante.core.interfaces.gateway.IUsuarioGateway;

public class UsuarioGateway implements IUsuarioGateway {

    private final IDataStorageUsuario dataSource;

    private UsuarioGateway(IDataStorageUsuario iDataStorageUsuario) {
        this.dataSource = iDataStorageUsuario;
    }

    public static UsuarioGateway create (IDataStorageUsuario iDataStorageUsuario) {
        return new UsuarioGateway(iDataStorageUsuario);
    }

    @Override
    public Usuario cadastrar(Usuario usuario) {

        UsuarioInputDTO dto = UsuarioInputDTO.fromEntity(usuario);

        final UsuarioOutputDTO dtoCriado = this.dataSource.cadastrar(dto);

        return Usuario.fromDTO(dtoCriado);
    }

    @Override
    public boolean deletar(Long id) {
        return false;
    }

    @Override
    public Usuario buscarUsuarioPorIdentificador(Long id) {
        return null;
    }

    @Override
    public Usuario buscarUsuarioPorLogin(String login) {
        return null;
    }
}