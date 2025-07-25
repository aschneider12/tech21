package br.com.fiap.restaurante.infra.service;

import br.com.fiap.restaurante.infra.database.entities.UsuarioEntity;
import br.com.fiap.restaurante.infra.database.repositories.jpa.UsuarioRepository;
import br.com.fiap.restaurante.infra.dtos.MudarSenhaDTO;
import br.com.fiap.restaurante.infra.dtos.UsuarioInsertDTO;
import br.com.fiap.restaurante.infra.dtos.UsuarioResponseDTO;
import br.com.fiap.restaurante.infra.dtos.UsuarioUpdateDTO;
import br.com.fiap.restaurante.domain.exceptions.ValidationException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Deprecated
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
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setNome(dto.nome());
        usuarioEntity.setEmail(dto.email());
        usuarioEntity.setLogin(dto.login());
        usuarioEntity.setSenha(passwordEncoder.encode(dto.senha()));
        usuarioEntity.setEndereco(dto.endereco());

        usuarioEntity.setDataUltimaAlteracao(LocalDateTime.now());

        repository.save(usuarioEntity);

        perfilService.adicionarPerfil(usuarioEntity.getId(), dto.perfis());

        return new UsuarioResponseDTO(usuarioEntity);
    }

    /*
        PERFIS e SENHA atualizados somente via controller proprio
     */
    public UsuarioResponseDTO atualizar(Long id, @Valid UsuarioUpdateDTO dto) {
        UsuarioEntity usuarioEntity = buscarUsuarioPorId(id);

        //TODO - usar maper, ja corre risco de ficar desatualizado
        usuarioEntity.setNome(dto.nome());
        usuarioEntity.setEmail(dto.email());

        if(dto.login() != null)
            usuarioEntity.setLogin(dto.login());

        usuarioEntity.setEndereco(dto.endereco());
        usuarioEntity.setDataUltimaAlteracao(LocalDateTime.now());

        UsuarioEntity atualizado = repository.save(usuarioEntity);
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

    public List<UsuarioEntity> buscarTodosUsuarios() {
        //TODO - pegar list do banco e transformar em stream()
        // mapear para DTO
        // ordenar direto no sort do stream

        //ordem invertida para facilitar a leitura do json
        return repository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public boolean validarLogin(String login, String senha) {

        UsuarioEntity usuarioEntityBD = repository.findByLogin(login)
                .orElseThrow(() -> new ValidationException("Usuário não encontrado"));

        return passwordEncoder.matches(senha, usuarioEntityBD.getSenha());
    }

    public void mudarSenha(MudarSenhaDTO dto, Long id) {
        UsuarioEntity usuarioEntity = buscarUsuarioPorId(id);

        if(!passwordEncoder.matches(dto.senhaAntiga(), usuarioEntity.getSenha()))
            throw new ValidationException("Senha antiga não confere.");

        if (!validarSenhaForte(dto.senhaNova())) {
            throw new ValidationException("Senha fraca. A senha deve ter pelo menos 8 caracteres e conter números.");
        }

        String novaSenhaHash = passwordEncoder.encode(dto.senhaNova());
        repository.updateNovaSenha(novaSenhaHash, usuarioEntity.getId());
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

    public UsuarioEntity buscarUsuarioPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ValidationException("Usuário não encontrado."));
    }
}

