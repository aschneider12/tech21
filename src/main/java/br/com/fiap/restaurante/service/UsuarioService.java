package br.com.fiap.restaurante.service;

import br.com.fiap.restaurante.dtos.UsuarioRequestDTO;
import br.com.fiap.restaurante.dtos.UsuarioResponseDTO;
import br.com.fiap.restaurante.entities.Usuario;
import br.com.fiap.restaurante.exceptions.LoginExistsException;
import br.com.fiap.restaurante.exceptions.SenhaFracaException;
import br.com.fiap.restaurante.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Camada de serviço, toda lógica de negócios deve ser inserida aqyi.
 */
@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository repository;

    // Método que converte DTO de requisição para entidade
    public Usuario converterParaEntidade(UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setLogin(dto.login());
        usuario.setSenha(dto.senha());
        usuario.setTipoUsuario(dto.tipoUsuario());
        usuario.setDataUltimaAlteracao(LocalDate.now()); // define data atual
        // Se tiver endereço ou outros campos, trate aqui também
        return usuario;
    }




    // Método que converte entidade para DTO de resposta
    public UsuarioResponseDTO converterParaDTO(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getLogin(),
                usuario.getDataUltimaAlteracao(),
                usuario.getTipoUsuario()
        );
    }







    public Usuario salvar(Usuario usuario) {

        //validações necessárias

        //senha forte?
        if (!senhaForte(usuario.getSenha())) {
            throw new SenhaFracaException("Senha fraca. " +
                    "A senha deve ter pelo menos 8 caracteres e conter números.");
        }

        //login ja existe?
        if (repository.existsByLogin(usuario.getLogin())) {
            throw new LoginExistsException("Login já existe, verifique");
        }



        //senha igual anterior?

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

    // Implementação da validação da senha
    private boolean senhaForte(String senha) {

        //  tamanho mínimo, caracteres especiais, números etc.
        return senha != null && senha.length() >= 8 && senha.matches(".*\\d.*");
    }


}
