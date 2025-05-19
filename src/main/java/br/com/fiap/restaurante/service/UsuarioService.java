package br.com.fiap.restaurante.service;

import br.com.fiap.restaurante.dtos.UsuarioRequestDTO;
import br.com.fiap.restaurante.dtos.UsuarioResponseDTO;
import br.com.fiap.restaurante.DTO.MudarSenhaDTO;
import br.com.fiap.restaurante.entities.Usuario;
import br.com.fiap.restaurante.exceptions.ValidationException;
import br.com.fiap.restaurante.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Camada de serviço, toda lógica de negócios deve ser inserida aqyi.
 */
@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository repository;

    public UsuarioResponseDTO cadastrar(UsuarioRequestDTO usuarioDTO) {

        Usuario usuario = converterParaEntidade(new Usuario(), usuarioDTO);
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

    private Usuario salvar(Usuario usuario) {

        //outras validações necessárias

        //senha forte?
        if (!senhaForte(usuario.getSenha())) {
            throw new ValidationException("Senha fraca. " +
                    "A senha deve ter pelo menos 8 caracteres e conter números.");
        }

        //login ja existe?
        if (repository.existsByLogin(usuario.getLogin())) {
            throw new ValidationException("Login já existe, verifique");
        }

        usuario.setDataUltimaAlteracao(LocalDate.now());

        return repository.save(usuario);
    }

    public void deletar(Long id) {
        if(id != null)
          repository.deleteById(id);
    }

    public List<Usuario> buscarTodosUsuarios() {
        return repository.findAll();
    }

    public void mudarSenha(MudarSenhaDTO mudarSenhaDTO, Long id) {
        var usuario = repository.findById(id).orElse(null);
        if (usuario.getSenha().equals(mudarSenhaDTO.senhaAntiga())) {
            repository.mudarSenha(mudarSenhaDTO.senhaNova(), id);
        }else{
            throw new ValidationException("Senha antiga incorreta");
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
}
