package br.com.fiap.restaurante.application.gateways;

import br.com.fiap.restaurante.core.domain.interfaces.gateway.IUsuarioGateway;
import br.com.fiap.restaurante.core.domain.interfaces.storage.IDataStorageUsuario;
import br.com.fiap.restaurante.core.domain.models.Usuario;

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

//        var dto = UsuarioMapper.toInput(usuario);

        var cadastrado = this.dataSource.cadastrar(usuario);

        return Usuario.fromDTO(cadastrado);
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