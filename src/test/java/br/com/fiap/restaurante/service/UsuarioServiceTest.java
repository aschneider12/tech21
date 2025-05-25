package br.com.fiap.restaurante.service;

import br.com.fiap.restaurante.entities.TipoUsuario;
import br.com.fiap.restaurante.entities.Usuario;
import br.com.fiap.restaurante.repositories.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioServiceTest {

    private UsuarioRepository repository;
    private UsuarioService service;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(UsuarioRepository.class);
        service = new UsuarioService();
        service.repository = repository;

        Usuario cliente = new Usuario();
        cliente.setLogin("cliente123");
        cliente.setSenha("senha123");
        cliente.setTipoUsuario(TipoUsuario.CLIENTE);

        Usuario dono = new Usuario();
        dono.setLogin("dono123");
        dono.setSenha("senha456");
        dono.setTipoUsuario(TipoUsuario.DONO);

        Mockito.when(repository.findAll()).thenReturn(List.of(cliente, dono));
    }

    @Test
    void deveValidarLoginCliente() {
        boolean resultado = service.validarLogin("cliente123", "senha123", TipoUsuario.CLIENTE);
        assertTrue(resultado, "Login de CLIENTE deveria ser válido");
    }

    @Test
    void deveValidarLoginDono() {
        boolean resultado = service.validarLogin("dono123", "senha456", TipoUsuario.DONO);
        assertTrue(resultado, "Login de DONO deveria ser válido");
    }

    @Test
    void naoDeveValidarLoginComTipoErrado() {
        boolean resultado = service.validarLogin("cliente123", "senha123", TipoUsuario.DONO);
        assertFalse(resultado, "Login com tipo incorreto não deveria ser válido");
    }

    @Test
    void naoDeveValidarLoginComSenhaIncorreta() {
        boolean resultado = service.validarLogin("dono123", "senhaErrada", TipoUsuario.DONO);
        assertFalse(resultado, "Login com senha errada não deveria ser válido");
    }
}
