package br.com.fiap.restaurante.service;

import br.com.fiap.restaurante.DTO.MudarSenhaDTO;
import br.com.fiap.restaurante.entities.TipoUsuario;
import br.com.fiap.restaurante.entities.Usuario;
import br.com.fiap.restaurante.exceptions.SenhaIncorretaException;
import br.com.fiap.restaurante.repositories.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario salvar(Usuario usuario) {
        if (usuario.getTipoUsuario() == null) {
            throw new IllegalArgumentException("Tipo de usuário deve ser informado.");
        }

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuario.setDataUltimaAlteracao(LocalDate.now());

        return repository.save(usuario);
    }

    public void deletar(Long id) {
        if (id != null)
            repository.deleteById(id);
    }

    public List<Usuario> buscarTodosUsuarios() {
        return repository.findAll();
    }

    public boolean validarLogin(String login, String senha, TipoUsuario tipoUsuario) {
        for (Usuario u : repository.findAll()) {
            System.out.println("---------- VERIFICAÇÃO DE LOGIN ----------");
            System.out.println("Login recebido: " + login);
            System.out.println("Login no banco: " + u.getLogin());
            System.out.println("Senha digitada: " + senha);
            System.out.println("Senha no banco: " + u.getSenha());
            System.out.println("Match senha? " + passwordEncoder.matches(senha, u.getSenha()));
            System.out.println("Tipo recebido: " + tipoUsuario);
            System.out.println("Tipo no banco: " + u.getTipoUsuario());
            System.out.println("Match tipo? " + tipoUsuario.equals(u.getTipoUsuario()));
            System.out.println("------------------------------------------");

            if (
                login.equals(u.getLogin()) &&
                passwordEncoder.matches(senha, u.getSenha()) &&
                tipoUsuario.equals(u.getTipoUsuario())
            ) {
                return true;
            }
        }
        return false;
    }

    public void trocarSenha(String login, String novaSenha) {
        Usuario usuario = repository.findAll().stream()
                .filter(u -> u.getLogin().equals(login))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        usuario.setSenha(passwordEncoder.encode(novaSenha));
        usuario.setDataUltimaAlteracao(LocalDate.now());
        repository.save(usuario);
    }

    public void mudarSenha(MudarSenhaDTO mudarSenhaDTO, Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        if (passwordEncoder.matches(mudarSenhaDTO.senhaAntiga(), usuario.getSenha())) {
            usuario.setSenha(passwordEncoder.encode(mudarSenhaDTO.senhaNova()));
            repository.save(usuario);
        } else {
            throw new SenhaIncorretaException("Senha incorreta");
        }
    }

    @PostConstruct
    public void gerarHashParaTeste() {
        String senha = "123456";
        String hash = passwordEncoder.encode(senha);
        System.out.println("Hash gerado via Spring context para \"123456\": " + hash);
    }
}

