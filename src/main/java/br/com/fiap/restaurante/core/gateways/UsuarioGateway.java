package br.com.fiap.restaurante.core.gateways;

import br.com.fiap.restaurante.core.domain.entities.Restaurante;
import br.com.fiap.restaurante.core.domain.entities.Usuario;
import br.com.fiap.restaurante.core.dtos.restaurante.RestauranteRetornoDTO;
import br.com.fiap.restaurante.core.dtos.usuario.UsuarioCadastroDTO;
import br.com.fiap.restaurante.core.dtos.usuario.UsuarioRetornoDTO;
import br.com.fiap.restaurante.core.interfaces.IDataStorageUsuario;
import br.com.fiap.restaurante.core.interfaces.IUsuarioGateway;

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

        UsuarioCadastroDTO dto = UsuarioCadastroDTO.fromEntity(usuario);

        final UsuarioRetornoDTO dtoCriado = this.dataSource.cadastrar(dto);

        return Usuario.fromDTO(dtoCriado);
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