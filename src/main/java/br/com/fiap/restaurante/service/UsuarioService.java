package br.com.fiap.restaurante.service;

import br.com.fiap.restaurante.dtos.MudarSenhaDTO;
import br.com.fiap.restaurante.dtos.UsuarioInsertDTO;
import br.com.fiap.restaurante.dtos.UsuarioResponseDTO;
import br.com.fiap.restaurante.dtos.UsuarioUpdateDTO;
import br.com.fiap.restaurante.entities.Usuario;
import br.com.fiap.restaurante.exceptions.ValidationException;
import br.com.fiap.restaurante.repositories.UsuarioRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UsuarioService {

    private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PerfilService perfilService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioResponseDTO cadastrar(UsuarioInsertDTO dto) {
        if (repository.existsByLogin(dto.login()))
            throw new ValidationException("Login já cadastrado.");

        validarSenhaForte(dto.senha());

        //TODO - usar mapper, ja tem risco de ficar desatualizado
        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setLogin(dto.login());
        usuario.setSenha(passwordEncoder.encode(dto.senha()));
        usuario.setEndereco(dto.endereco());

        usuario.setDataUltimaAlteracao(LocalDateTime.now());

        repository.save(usuario);

        perfilService.adicionarPerfil(usuario.getId(), dto.perfis());

        return new UsuarioResponseDTO(usuario);
    }

    /*
        PERFIS e SENHA atualizados somente via controller proprio
     */
    public UsuarioResponseDTO atualizar(Long id, @Valid UsuarioUpdateDTO dto) {
        Usuario usuario = buscarUsuarioPorId(id);

        //TODO - usar maper, ja corre risco de ficar desatualizado
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());

        if(dto.login() != null)
            usuario.setLogin(dto.login());

        usuario.setEndereco(dto.endereco());
        usuario.setDataUltimaAlteracao(LocalDateTime.now());

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
        //TODO - pegar list do banco e transformar em stream()
        // mapear para DTO
        // ordenar direto no sort do stream

        //ordem invertida para facilitar a leitura do json
        return repository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public Usuario salvar(Usuario usuario) {
        if (!validarSenhaForte(usuario.getSenha())) {
            throw new ValidationException("Senha fraca. A senha deve ter pelo menos 8 caracteres e conter números.");
        }

        if (repository.existsByLogin(usuario.getLogin())) {
            throw new ValidationException("Login já cadastrado.");
        }

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuario.setDataUltimaAlteracao(LocalDateTime.now());
        return repository.save(usuario);
    }

    public boolean validarLogin(String login, String senha) {

        Usuario usuarioBD = repository.findByLogin(login)
                .orElseThrow(() -> new ValidationException("Usuário não encontrado"));

        return passwordEncoder.matches(senha, usuarioBD.getSenha());
    }

    public void mudarSenha(MudarSenhaDTO dto, Long id) {
        Usuario usuario = buscarUsuarioPorId(id);

        if(!passwordEncoder.matches(dto.senhaAntiga(), usuario.getSenha()))
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
            System.out.println("Senha no validador: " + senha); // Adicione esta linha para depurar

            if (!senha.matches(".*[A-Z].*"))
                throw new ValidationException("Senha deve conter pelo menos uma letra maiuscula!");
            if (!senha.matches(".*\\d.*"))
                throw new ValidationException("Senha deve conter pelo menos um número.");
            if (!senha.matches(".*[^a-zA-Z0-9].*"))
                throw new ValidationException("Senha deve conter pelo menos um caracter especial aaaa.");
            if (senha.length() < 8)
                throw new ValidationException("Senha deve ter no minimo 8 caracteres.");
        }
        return true;
    }

    public Usuario buscarUsuarioPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ValidationException("Usuário não encontrado."));
    }
}

