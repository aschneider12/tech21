package br.com.fiap.restaurante.application.usecases.usuario;

import br.com.fiap.restaurante.application.usecases.usuario.perfil.UseCaseAdicionarPerfisUsuario;
import br.com.fiap.restaurante.application.usecases.usuario.perfil.UseCaseBuscarPerfisUsuario;
import br.com.fiap.restaurante.application.usecases.usuario.perfil.UseCaseRemoverPerfisUsuario;
import br.com.fiap.restaurante.application.usecases.usuario.senha.UseCaseAlterarSenhaUsuario;
import br.com.fiap.restaurante.domain.interfaces.gateway.IUsuarioGateway;
import org.springframework.security.crypto.password.PasswordEncoder;

public class FactoryUsuarioUseCase {

    private final IUsuarioGateway gateway;
    private final PasswordEncoder passwordEncoder;

    public FactoryUsuarioUseCase(IUsuarioGateway gateway, PasswordEncoder passwordEncoder) {
        this.gateway = gateway;
        this.passwordEncoder = passwordEncoder;
    }

    public UseCaseAtualizarUsuario atualizarUsuario() {
        return UseCaseAtualizarUsuario.create(gateway);
    }

    public UseCaseBuscarTodosUsuarios buscarTodosUsuarios() {
        return UseCaseBuscarTodosUsuarios.create(gateway);
    }

    public UseCaseBuscarUsuarioPorID buscarUsuarioPorID() {
        return UseCaseBuscarUsuarioPorID.create(gateway);
    }

    public UseCaseCadastrarUsuario cadastrarUsuario(){
        return UseCaseCadastrarUsuario.create(gateway);
    }

    public UseCaseDeletarUsuario deletarUsuario(){
        return UseCaseDeletarUsuario.create(gateway);
    }

    public UseCaseBuscarPerfisUsuario buscarPerfisUsuario(){
        return UseCaseBuscarPerfisUsuario.create(gateway);
    }
    public UseCaseAdicionarPerfisUsuario adicionarPerfisUsuario(){
        return UseCaseAdicionarPerfisUsuario.create(gateway);
    }

    public UseCaseRemoverPerfisUsuario removerPerfisUsuario(){
        return UseCaseRemoverPerfisUsuario.create(gateway);
    }
    public UseCaseAlterarSenhaUsuario alterarSenhaUsuario(){
        return UseCaseAlterarSenhaUsuario.create(gateway, passwordEncoder);
    }





}
