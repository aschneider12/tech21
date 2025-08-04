package br.com.fiap.restaurante.application.usecases.usuario;

import br.com.fiap.restaurante.application.exceptions.EntidadeNaoEncontradaException;
import br.com.fiap.restaurante.application.input.UsuarioInput;
import br.com.fiap.restaurante.application.output.UsuarioOutput;
import br.com.fiap.restaurante.domain.interfaces.gateway.IUsuarioGateway;
import br.com.fiap.restaurante.domain.models.Usuario;
import br.com.fiap.restaurante.application.exceptions.EntidadeJaExisteException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

/**
 * Realilza o cadastro de um novo usuario.
 */
public class UseCaseCadastrarUsuario {

    private final IUsuarioGateway gateway;

    private final PasswordEncoder passwordEncoder;

    private UseCaseCadastrarUsuario(IUsuarioGateway gateway, PasswordEncoder passwordEncoder){
        this.gateway = gateway;
        this.passwordEncoder = passwordEncoder;
    }

    public static UseCaseCadastrarUsuario create(IUsuarioGateway gateway, PasswordEncoder passwordEncoder) {
        return new UseCaseCadastrarUsuario(gateway, passwordEncoder);
    }

    public UsuarioOutput run(UsuarioInput input) {

        final Usuario usuarioExistente = gateway.buscarUsuarioPorLogin(input.login());
        if (usuarioExistente != null)
            throw new EntidadeJaExisteException("Usuário", input.login());

        var usuarioCadastrar = UsuarioInput.toDomain(input);

        usuarioCadastrar.validacoesDominio();

        usuarioCadastrar.setSenha(passwordEncoder.encode(usuarioCadastrar.getSenha()));

        Set<String> perfisTemp = usuarioCadastrar.getPerfis();
        usuarioCadastrar.setPerfis(null);

        Usuario cadastrado = gateway.cadastrar(usuarioCadastrar);
        cadastrado.setPerfis(perfisTemp);
        gateway.atualizar(cadastrado);

        return UsuarioOutput.fromDomain(cadastrado);
    }
}
