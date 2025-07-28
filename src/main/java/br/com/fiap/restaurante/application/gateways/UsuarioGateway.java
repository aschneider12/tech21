package br.com.fiap.restaurante.application.gateways;

import br.com.fiap.restaurante.domain.interfaces.gateway.IUsuarioGateway;
import br.com.fiap.restaurante.domain.interfaces.storage.IDataStorageUsuario;
import br.com.fiap.restaurante.domain.models.Usuario;

import java.util.List;

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

        return cadastrado;
    }

    @Override
    public Usuario atualizar(Usuario usuario) {

        return dataSource.atualizar(usuario);
    }

    @Override
    public boolean deletar(Long id) {

        return dataSource.deletar(id);
    }

    @Override
    public List<Usuario> buscarTodosUsuarios() {

        return dataSource.buscarTodosUsuarios();
    }

    @Override
    public Usuario buscarUsuarioPorIdentificador(Long id) {

        return dataSource.buscarUsuarioPorIdentificador(id);
    }

    @Override
    public Usuario buscarUsuarioPorLogin(String login) {

        return dataSource.buscarUsuarioPorLogin(login);
    }


    @Override
    public List<String> buscarPerfisUsuario(Long id) {

//        dataSource.bu
        return null;
    }

    @Override
    public void atualizarNovaSenhaUsuario(Long id, String newPasswordHash) {
        dataSource.atualizarNovaSenha(id, newPasswordHash);
    }
}