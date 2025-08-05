package br.com.fiap.restaurante.infra.database.repositories.adapter;

import br.com.fiap.restaurante.domain.models.Usuario;
import br.com.fiap.restaurante.helper.Helper;
import br.com.fiap.restaurante.infra.database.mappers.UsuarioEntityMapper;
import br.com.fiap.restaurante.infra.database.repositories.jpa.UsuarioRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
//@AutoConfigureTestDatabase
@Transactional
@Sql(scripts = {"/db_restaurante_clean.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Sql(scripts = {"/db_restaurante_load.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class UsuarioRepositoryAdapterIT {


    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioRepositoryAdapter adapter;

    @Autowired
    private EntityManager entityManager;


    @Test
    void devePermitirAtualizarSenha(){

        var senhaNova = "Nova senha";
        var id = 9999L;

        var usuarioExistenteEntity = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado para atualização"));

        String senhaAntiga = usuarioExistenteEntity.getSenha();

        adapter.atualizarNovaSenha(id, senhaNova);

        entityManager.flush(); // força sincronização com o banco
        entityManager.clear(); // limpa o cache do JPA


        var usuarioPosMudanca = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado para atualização"));


        assertThat(usuarioPosMudanca.getSenha())
                .isNotEqualTo(senhaAntiga);

        assertThat(usuarioPosMudanca.getSenha())
                .isEqualTo(senhaNova);

    }
    @Test
    void deveVerificarSeUsuarioExistePorLogin() {
        String email = "joaoteste";

        var usuarioFoiEcontrado = adapter.existsByLogin(email);

        assertThat(usuarioFoiEcontrado)
                .isTrue();
    }

    @Test
    void deveVerificarSeUsuarioExistePorEmail() {
        String email = "joao@teste.com";

        var usuarioFoiEcontrado = adapter.existsByEmail(email);

        assertThat(usuarioFoiEcontrado)
                .isTrue();
    }

    @Test
    void devePermtirCadastrarUsuario(){

        var usuario = Helper.gerarUsuario();
        usuario.getEndereco().setId(null);

        var usuarioCadastrado = adapter.cadastrar(usuario);

        assertThat(usuarioCadastrado)
                .isNotNull()
                .isInstanceOf(Usuario.class);

        assertThat(usuarioCadastrado.getNome())
                .isEqualTo(usuario.getNome());

        assertThat(usuarioCadastrado.getLogin())
                .isEqualTo(usuario.getLogin());

        assertThat(usuarioCadastrado.getEmail())
                .isEqualTo(usuario.getEmail());

    }

    @Test
    void devePermitirAtualizarUsuario() {

        var usuarioExistenteEntity = usuarioRepository.findById(9999L)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado para atualização"));

        var usuarioExistente = UsuarioEntityMapper.INSTANCE.toDomain(usuarioExistenteEntity);

        String novoNome = "Nome Atualizado";
        String novoEmail = "novo@email.com";
        usuarioExistente.setNome(novoNome);
        usuarioExistente.setEmail(novoEmail);

        var usuarioAtualizado = adapter.atualizar(usuarioExistente);


        assertThat(usuarioAtualizado)
                .isNotNull();

        assertThat(usuarioAtualizado.getId())
                .isEqualTo(usuarioExistente.getId());

        assertThat(usuarioAtualizado.getNome())
                .isEqualTo(novoNome);

        assertThat(usuarioAtualizado.getEmail())
                .isEqualTo(novoEmail);
    }

    @Test
    void devePermitirDeletarUsuario(){
        Long id = 9999L;

        var usuarioFoiRemovido = adapter.deletar(id);

        assertThat(usuarioFoiRemovido)
                .isTrue();
    }


    @Test
    void devePermitirBuscarUsuarioPorLogin(){
       String login = "joaoteste";

        var usuarioEncontrado = adapter.buscarUsuarioPorLogin(login);

        assertThat(usuarioEncontrado)
                .isNotNull()
                .isInstanceOf(Usuario.class);
    }

    @Test
    void devePermitirBuscarUsuarioPorId(){
        Long id = 9999L;

        var usuarioEncontrado = adapter.buscarUsuarioPorIdentificador(id);

        assertThat(usuarioEncontrado)
                .isNotNull()
                .isInstanceOf(Usuario.class);
    }

    @Test
    void devePermtirBuscarTodosRestaurantes() {
        List<Usuario> usuarios = adapter.buscarTodosUsuarios();

        assertThat(usuarios)
                .isNotNull();
        assertThat(usuarios)
                .isNotEmpty();
    }



}
