package br.com.fiap.restaurante.service;

import br.com.fiap.restaurante.dtos.UsuarioRequestDTO;
import br.com.fiap.restaurante.dtos.UsuarioResponseDTO;
import br.com.fiap.restaurante.dtos.MudarSenhaDTO;
import br.com.fiap.restaurante.entities.TipoUsuario;
import br.com.fiap.restaurante.entities.Usuario;
import br.com.fiap.restaurante.exceptions.ValidationException;
import br.com.fiap.restaurante.repositories.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public UsuarioResponseDTO cadastrar(UsuarioRequestDTO usuarioDTO) {

        Usuario usuario = converterParaEntidade(new Usuario(), usuarioDTO);
        salvar(usuario);
        return converterParaDTO(usuario);
    }

    public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO usuarioDTO) {
        Optional<Usuario> usrBD = repository.findById(id);

        if(usrBD.isEmpty())
            throw new ValidationException("Usuário com ID "+id+" não encontrado!");

        Usuario usuario = converterParaEntidade(usrBD.get(), usuarioDTO);

        salvar(usuario);

        return converterParaDTO(usuario);
    }

    public Usuario salvar(Usuario usuario) {

        if (usuario.getTipoUsuario() == null) {
            throw new ValidationException("Tipo de usuário deve ser informado.");
        }
        //senha forte?
        if (!senhaForte(usuario.getSenha())) {
            throw new ValidationException("Senha fraca. " +
                    "A senha deve ter pelo menos 8 caracteres e conter números.");
        }

        //login ja existe?
        if (repository.existsByLogin(usuario.getLogin())) {
            throw new ValidationException("Login já existe, verifique");
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

    public void mudarSenha(MudarSenhaDTO mudarSenhaDTO, Long id) {

        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new ValidationException("Usuário não encontrado"));

        if (passwordEncoder.matches(mudarSenhaDTO.senhaAntiga(), usuario.getSenha())) {
            usuario.setSenha(passwordEncoder.encode(mudarSenhaDTO.senhaNova()));
            usuario.setDataUltimaAlteracao(LocalDate.now());
            repository.save(usuario);
        } else {
            throw new ValidationException("Senha incorreta");
        }
    }

    // Implementação da validação da senha
    private boolean senhaForte(String senha) {

        //  tamanho mínimo, caracteres especiais, números etc.
        return senha != null && senha.length() >= 8 && senha.matches(".*\\d.*");
    }

    // Método que converte DTO de requisição para entidade
    private Usuario converterParaEntidade(Usuario usuario, UsuarioRequestDTO dto) {

        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setLogin(dto.login());
        usuario.setSenha(dto.senha());
        usuario.setTipoUsuario(dto.tipoUsuario());
        // Se tiver endereço ou outros campos, trate aqui também
        return usuario;
    }

    // Método que converte entidade para DTO de resposta
    private UsuarioResponseDTO converterParaDTO(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getLogin(),
                usuario.getDataUltimaAlteracao(),
                usuario.getTipoUsuario()
        );
    }

    @PostConstruct
    public void gerarHashParaTeste() {
        String senha = "123456";
        String hash = passwordEncoder.encode(senha);
        System.out.println("Hash gerado via Spring context para \"123456\": " + hash);
    }
}

