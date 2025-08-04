package br.com.fiap.restaurante.application.usecases.usuario;

import br.com.fiap.restaurante.application.usecases.usuario.perfil.UseCaseAdicionarPerfisUsuario;
import br.com.fiap.restaurante.application.usecases.usuario.perfil.UseCaseBuscarPerfisUsuario;
import br.com.fiap.restaurante.application.usecases.usuario.perfil.UseCaseRemoverPerfisUsuario;
import br.com.fiap.restaurante.application.usecases.usuario.senha.UseCaseAlterarSenhaUsuario;
import br.com.fiap.restaurante.application.usecases.usuario.senha.UseCaseGerarTokenUsuario;
import br.com.fiap.restaurante.application.usecases.usuario.senha.UseCaseValidarLogin;
import br.com.fiap.restaurante.application.usecases.usuario.senha.UseCaseValidarToken;
import br.com.fiap.restaurante.domain.interfaces.gateway.IUsuarioGateway;
import br.com.fiap.restaurante.infra.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;

public class FactoryUsuarioUseCase {

    private final IUsuarioGateway gateway;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public FactoryUsuarioUseCase(IUsuarioGateway gateway,
                                 PasswordEncoder passwordEncoder,
                                 JwtService jwtService) {
        this.gateway = gateway;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
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
        return UseCaseCadastrarUsuario.create(gateway, passwordEncoder);
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

    public UseCaseValidarToken validarToken(){
        return UseCaseValidarToken.create(jwtService);
    }

    public UseCaseValidarLogin validarLogin(){
        return UseCaseValidarLogin.create(gateway,passwordEncoder);
    }
    public UseCaseGerarTokenUsuario gerarToken() {
        return UseCaseGerarTokenUsuario.create(gateway,jwtService);
    }
}
