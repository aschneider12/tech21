package br.com.fiap.restaurante.service;

import br.com.fiap.restaurante.dtos.UsuarioRequestDTO;
import br.com.fiap.restaurante.dtos.UsuarioResponseDTO;
import br.com.fiap.restaurante.dtos.MudarSenhaDTO;
import br.com.fiap.restaurante.entities.Usuario;
import br.com.fiap.restaurante.entities.TipoUsuario;
import br.com.fiap.restaurante.exceptions.ValidationException;
import br.com.fiap.restaurante.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;

@Service
public class UsuarioService {

    private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioResponseDTO cadastrar(UsuarioRequestDTO dto) {
        if (repository.existsByLogin(dto.login())) {
            throw new ValidationException("Login já cadastrado.");
        }

        if (!validarSenhaForte(dto.senha())) {
            throw new ValidationException("Senha fraca. A senha deve ter pelo menos 8 caracteres e conter números.");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setLogin(dto.login());
        usuario.setSenha(passwordEncoder.encode(dto.senha()));
        usuario.setTipoUsuario(dto.tipoUsuario());
        usuario.setDataUltimaAlteracao(LocalDate.now());

        Usuario salvo = repository.save(usuario);

        return new UsuarioResponseDTO(salvo);
    }

    public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO dto) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new ValidationException("Usuário não encontrado"));

        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setLogin(dto.login());
        usuario.setTipoUsuario(dto.tipoUsuario());
        usuario.setDataUltimaAlteracao(LocalDate.now());

        Usuario atualizado = repository.save(usuario);
        return new UsuarioResponseDTO(atualizado);
    }

    public void deletar(Long id) {
        if(repository.existsById(id))
            repository.deleteById(id);
        else
            throw new ValidationException("Usuário não existe!");
    }

    public List<UsuarioResponseDTO> buscarTodosDTO() {
        return repository.findAll()
                .stream()
                .map(UsuarioResponseDTO::new)
                .toList();
    }

    public List<Usuario> buscarTodosUsuarios() {
        return repository.findAll();
    }

    public Usuario salvar(Usuario usuario) {
        if (!validarSenhaForte(usuario.getSenha())) {
            throw new ValidationException("Senha fraca. A senha deve ter pelo menos 8 caracteres e conter números.");
        }

        if (repository.existsByLogin(usuario.getLogin())) {
            throw new ValidationException("Login já cadastrado.");
        }

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuario.setDataUltimaAlteracao(LocalDate.now());
        return repository.save(usuario);
    }

    public boolean validarLogin(String login, String senha, TipoUsuario tipoUsuario) {
        return repository.findAll().stream()
                .anyMatch(u ->
                        u.getLogin().equals(login) &&
                        passwordEncoder.matches(senha, u.getSenha()) &&
                        u.getTipoUsuario().equals(tipoUsuario));
    }

    public void mudarSenha(MudarSenhaDTO dto, Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new ValidationException("Usuário não encontrado."));

        if(!validarLogin(usuario.getLogin(), dto.senhaAntiga(),usuario.getTipoUsuario()))
            throw new ValidationException("Senha antiga não confere.");

        if (!validarSenhaForte(dto.senhaNova())) {
            throw new ValidationException("Senha fraca. A senha deve ter pelo menos 8 caracteres e conter números.");
        }

        String novaSenhaHash = passwordEncoder.encode(dto.senhaNova());
        repository.mudarSenha(novaSenhaHash, usuario.getId());
        log.info("Senha alterada com sucesso para o usuário com ID: {}", id);
    }

    private boolean validarSenhaForte(String senha) {

        if(senha != null) {
            if (!senha.matches(".*[A-Z].*"))
                throw new ValidationException("Senha deve conter pelo menos uma letra maiuscula!");
            if (!senha.matches(".*\\d.*"))
                throw new ValidationException("Senha deve conter pelo menos um número.");
            if (!senha.matches(".*[^a-zA-Z0-9].*"))
                throw new ValidationException("Senha deve conter pelo menos um caracter especial.");
            if (senha.length() < 8)
                throw new ValidationException("Senha deve ter no minimo 8 caracteres.");
        }
        return true;
    }
}

