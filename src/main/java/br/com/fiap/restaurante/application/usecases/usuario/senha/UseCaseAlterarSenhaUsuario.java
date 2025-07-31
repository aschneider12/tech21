package br.com.fiap.restaurante.application.usecases.usuario.senha;

import br.com.fiap.restaurante.application.exceptions.EntidadeNaoEncontradaException;
import br.com.fiap.restaurante.application.exceptions.ValidationException;
import br.com.fiap.restaurante.domain.interfaces.gateway.IUsuarioGateway;
import br.com.fiap.restaurante.domain.models.Usuario;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UseCaseAlterarSenhaUsuario {

    private final PasswordEncoder passwordEncoder;

    private final IUsuarioGateway gateway;

    private UseCaseAlterarSenhaUsuario(IUsuarioGateway gateway, PasswordEncoder passwordEncoder){
        this.gateway = gateway;
        this.passwordEncoder = passwordEncoder;
    }

    public static UseCaseAlterarSenhaUsuario create(IUsuarioGateway gateway, PasswordEncoder passwordEncoder) {
        return new UseCaseAlterarSenhaUsuario(gateway, passwordEncoder);
    }

    public void run(Long idUsuario, String oldPassword, String newPassword)
            throws ValidationException {

        try {

            Usuario usuario = gateway.buscarUsuarioPorIdentificador(idUsuario);

            if(!passwordEncoder.matches(oldPassword, usuario.getSenha()))
                throw new ValidationException("Senha antiga não confere.");

            validarSenhaForte(newPassword);

            String novaSenhaHash = passwordEncoder.encode(newPassword);

            gateway.atualizarNovaSenhaUsuario(usuario.getId(), novaSenhaHash);

        } catch (EntidadeNaoEncontradaException ex){

            throw new ValidationException(ex.getMessage());
        }
    }

    private void validarSenhaForte(String senha) {

        if(senha == null)
            throw new ValidationException("Senha não pode ser nula!");
        if (!senha.matches(".*[A-Z].*"))
            throw new ValidationException("Senha deve conter pelo menos uma letra maiuscula!");
        if (!senha.matches(".*\\d.*"))
            throw new ValidationException("Senha deve conter pelo menos um número.");
        if (!senha.matches(".*[^a-zA-Z0-9].*"))
            throw new ValidationException("Senha deve conter pelo menos um caracter especial.");
        if (senha.length() < 8)
            throw new ValidationException("Senha deve ter no minimo 8 caracteres.");

    }

}
