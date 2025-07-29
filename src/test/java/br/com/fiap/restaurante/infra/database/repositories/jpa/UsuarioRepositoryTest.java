package br.com.fiap.restaurante.infra.database.repositories.jpa;

import br.com.fiap.restaurante.helper.Helper;
import br.com.fiap.restaurante.infra.database.entities.UsuarioEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

public class UsuarioRepositoryTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    AutoCloseable mock;

    @BeforeEach
    void setup(){
        mock = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void teardown() throws Exception{
        mock.close();
    }


    @Test
    void devePermitirCadastrarUsuario(){
       var usuario = Helper.gerarUsuarioEntity();
       when(usuarioRepository.save(any(UsuarioEntity.class))).thenReturn(usuario);

       var usuarioArmazenado = usuarioRepository.save(usuario);

       verify(usuarioRepository, times(1)).save(usuario);

    }

    @Test
    void deveConsultarUsuarioPorLogin(){
       var id = 1L;
       var usuario = Helper.gerarUsuarioEntity();
       usuario.setId(id);

       when(usuarioRepository.findByLogin(any(String.class))).thenReturn(Optional.of(usuario));

       var usuarioEncontrado = usuarioRepository.findByLogin("edu");

       assertThat(usuarioEncontrado)
               .isNotNull()
               .contains(usuario);
        verify(usuarioRepository, times(1)).findByLogin(any(String.class));

    }

    @Test
    void devePermitirAtualizarSenha(){
        var senhaNova = "12345";
        var id = 1L;

        doNothing().when(usuarioRepository).updateNovaSenha(any(String.class), any(Long.class));

        usuarioRepository.updateNovaSenha(senhaNova, id);

        verify(usuarioRepository, times(1)).updateNovaSenha(any(String.class), any(Long.class));

    }

    @Test
    void deveVerificarSeUsuarioExistePorLogin() {

        when(usuarioRepository.existsByLogin(any(String.class))).thenReturn(true);

        var usuarioExistente = usuarioRepository.existsByLogin("edu");

        assertThat(usuarioExistente)
                .isTrue();

    }

    @Test
    void deveVerificarSeUsuarioExistePorEmail() {
        when(usuarioRepository.existsByEmail(any(String.class))).thenReturn(true);

        var usuarioExistente = usuarioRepository.existsByEmail("edu@fiap.com.br");

        assertThat(usuarioExistente)
                .isTrue();

    }

}
