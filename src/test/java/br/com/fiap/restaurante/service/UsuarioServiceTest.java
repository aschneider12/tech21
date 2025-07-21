package br.com.fiap.restaurante.service;

import br.com.fiap.restaurante.application.dtos.MudarSenhaDTO;
import br.com.fiap.restaurante.application.entities.Usuario;
import br.com.fiap.restaurante.application.exceptions.ValidationException;
import br.com.fiap.restaurante.application.repositories.UsuarioRepository;
import br.com.fiap.restaurante.application.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Usuario de Testes");
        usuario.setEmail("tester@email.com");
        usuario.setLogin("tester");
        usuario.setSenha("Senha@123");
        usuario.setDataUltimaAlteracao(LocalDateTime.now());
//        usuario.set
    }

    @Test
    void deveRetornarTodosUsuarios() {
        when(repository.findAll()).thenReturn(List.of(usuario));
        List<Usuario> resultado = usuarioService.buscarTodosUsuarios();
        assertEquals(1, resultado.size());
        assertEquals("Tester", resultado.get(0).getLogin());
    }

    @Test
    void deveLancarExcecaoParaSenhaFraca() {
        usuario.setSenha("S@aaaaaa123");
        assertThrows(ValidationException.class, () -> usuarioService.salvar(usuario));
    }

    @Test
    void deveSalvarUsuarioComSenhaCriptografada() {
        when(passwordEncoder.encode(anyString())).thenReturn("hashMockado");
        when(repository.existsByLogin("Raque@l123")).thenReturn(false);
        when(repository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario salvo = usuarioService.salvar(usuario);

        assertNotNull(salvo);
        verify(repository).save(any(Usuario.class));
        verify(passwordEncoder).encode("senhaSegura123");
    }

    @Test
    void deveValidarLoginComCredenciaisValidas() {
        usuario.setSenha("senhaCriptografada");
        when(repository.findAll()).thenReturn(List.of(usuario));
        when(passwordEncoder.matches("senhaSegura123", "senhaCriptografada")).thenReturn(true);

        boolean resultado = usuarioService.validarLogin("raquel123", "senhaSegura123");
        assertTrue(resultado);
    }

    @Test
    void naoDeveValidarLoginComCredenciaisInvalidas() {
        when(repository.findAll()).thenReturn(List.of(usuario));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);

        boolean resultado = usuarioService.validarLogin("raquel123", "senhaErrada");
        assertFalse(resultado);
    }

    @Test
    void deveAtualizarSenhaComSucesso() {
        when(repository.findByLogin("raquel123")).thenReturn(Optional.of(usuario));
        when(passwordEncoder.encode("NovaSenha123")).thenReturn("novaSenhaCriptografada");

        usuarioService.mudarSenha( new MudarSenhaDTO("NovaSenha123","novaSenhaCriptografada"), usuario.getId());

        verify(repository).updateNovaSenha("novaSenhaCriptografada", usuario.getId());
    }

    @Test
    void deveLancarExcecaoAoAtualizarSenhaFraca() {

        when(repository.findByLogin("raquel123")).thenReturn(Optional.of(usuario));
        assertThrows(ValidationException.class, () -> usuarioService.mudarSenha(new MudarSenhaDTO("senhaSegura123", "123"), usuario.getId()));
    }
}

