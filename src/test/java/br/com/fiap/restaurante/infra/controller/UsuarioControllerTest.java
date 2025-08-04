package br.com.fiap.restaurante.infra.controller;

import br.com.fiap.restaurante.application.input.UsuarioInput;
import br.com.fiap.restaurante.application.output.UsuarioOutput;
import br.com.fiap.restaurante.application.usecases.usuario.*;
import br.com.fiap.restaurante.application.usecases.usuario.senha.UseCaseAlterarSenhaUsuario;
import br.com.fiap.restaurante.helper.Helper;
import br.com.fiap.restaurante.infra.controller.handlers.ExceptionController;
import br.com.fiap.restaurante.infra.dtos.MudarSenhaDTO;
import br.com.fiap.restaurante.infra.dtos.UsuarioInsertDTO;
import br.com.fiap.restaurante.infra.dtos.UsuarioResponseDTO;
import br.com.fiap.restaurante.infra.dtos.UsuarioUpdateDTO;
import br.com.fiap.restaurante.infra.mappers.UsuarioDTOMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UsuarioControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FactoryUsuarioUseCase factoryUseCase;

    @Mock
    private UseCaseCadastrarUsuario useCaseCadastrar;
    @Mock
    private UseCaseAtualizarUsuario useCaseAtualizar;

    @Mock
    private UseCaseDeletarUsuario useCaseDeletar;

    @Mock
    private UseCaseBuscarTodosUsuarios useCaseBuscarTodos;

    @Mock
    private UseCaseBuscarUsuarioPorID useCaseBuscarPorId;

    @Mock
    private UseCaseAlterarSenhaUsuario useCaseAlterarSenha;

    private AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        UsuarioController controller = new UsuarioController(factoryUseCase);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ExceptionController())
                .addFilter((req, res, chain) -> {
                    res.setCharacterEncoding("UTF-8");
                    chain.doFilter(req, res);
                }, "/*")
                .build();
    }

    @AfterEach
    void teardown() throws Exception {
        mock.close();
    }

    @Test
    void devePermitirCadastrarUsuario() throws Exception {
        var usuario = Helper.gerarUsuario();
        usuario.setId(1L);

        UsuarioInsertDTO usuarioDto = Helper.gerarUsuarioInsertDTO(usuario);
        UsuarioInput input = UsuarioDTOMapper.INSTANCE.toInputApplication(usuarioDto);
        UsuarioOutput output = UsuarioOutput.fromDomain(usuario);
        UsuarioResponseDTO usuarioDtoEsperado = UsuarioDTOMapper.INSTANCE.toResponse(output);

        when(factoryUseCase.cadastrarUsuario()).thenReturn(useCaseCadastrar);
        when(useCaseCadastrar.run(input)).thenReturn(output);

        mockMvc.perform(post("/usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(usuarioDto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(asJsonString(usuarioDtoEsperado)));

        verify(factoryUseCase, times(1)).cadastrarUsuario();
        verify(useCaseCadastrar, times(1)).run(input);
    }

    @Test
    void devePermtirAtualizarUsuario() throws Exception {
        Long idUsuario = 1L;
        var usuario = Helper.gerarUsuario();
        usuario.setId(idUsuario);

        UsuarioUpdateDTO usuarioDto = Helper.gerarUsuarioUpdateDTO(usuario);
        UsuarioInput input = UsuarioDTOMapper.INSTANCE.toInputApplication(usuarioDto);
        UsuarioOutput output = UsuarioOutput.fromDomain(usuario);
        UsuarioResponseDTO usuarioDtoEsperado = UsuarioDTOMapper.INSTANCE.toResponse(output);

        System.out.println(input);
        when(factoryUseCase.atualizarUsuario()).thenReturn(useCaseAtualizar);
        when(useCaseAtualizar.run(idUsuario, input)).thenReturn(output);

        mockMvc.perform(put("/usuario/{id}", idUsuario)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(usuarioDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(usuarioDtoEsperado)));

        verify(factoryUseCase).atualizarUsuario();
        verify(useCaseAtualizar).run(idUsuario, input);
    }

    @Test
    void devePermtirDeletarUsuario() throws Exception {
        Long idUsuario = 1L;

        when(factoryUseCase.deletarUsuario()).thenReturn(useCaseDeletar);
        when(useCaseDeletar.run(idUsuario)).thenReturn(true);

        mockMvc.perform(delete("/usuario/{id}", idUsuario))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andExpect(content().string("Usuário deletado!"));

        verify(factoryUseCase, times(1)).deletarUsuario();
        verify(useCaseDeletar, times(1)).run(idUsuario);
    }

    @Test
    void devePermtirBuscarTodosOsUsuarios() throws Exception {
        var usuario1 = Helper.gerarUsuario();
        usuario1.setId(1L);

        var usuario2 = Helper.gerarUsuario();
        usuario2.setId(2L);

        List<UsuarioOutput> outputs = List.of(
                UsuarioOutput.fromDomain(usuario1),
                UsuarioOutput.fromDomain(usuario2)
        );

        List<UsuarioResponseDTO> usuariosDtoEsperados = UsuarioDTOMapper.INSTANCE.toResponse(outputs);

        when(factoryUseCase.buscarTodosUsuarios()).thenReturn(useCaseBuscarTodos);
        when(useCaseBuscarTodos.run()).thenReturn(outputs);

        mockMvc.perform(get("/usuario"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(usuariosDtoEsperados)));

        verify(factoryUseCase, times(1)).buscarTodosUsuarios();
        verify(useCaseBuscarTodos, times(1)).run();
    }

    @Test
    void devePermtirBuscarUsuarioPorId() throws Exception {
        Long idUsuario = 1L;
        var usuario = Helper.gerarUsuario();
        usuario.setId(idUsuario);


        UsuarioOutput output = UsuarioOutput.fromDomain(usuario);
        UsuarioResponseDTO usuarioDtoEsperado = UsuarioDTOMapper.INSTANCE.toResponse(output);

        when(factoryUseCase.buscarUsuarioPorID()).thenReturn(useCaseBuscarPorId);
        when(useCaseBuscarPorId.run(idUsuario)).thenReturn(output);

        mockMvc.perform(get("/usuario/{id}", idUsuario))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(usuarioDtoEsperado)));

        verify(factoryUseCase, times(1)).buscarUsuarioPorID();
        verify(useCaseBuscarPorId, times(1)).run(idUsuario);
    }


    @Test
    void devePermtirAlterarSenhaDoUsuario() throws Exception {
        Long idUsuario = 1L;
        MudarSenhaDTO dto = new MudarSenhaDTO("senhaAntiga123", "senhaNova456");

        when(factoryUseCase.alterarSenhaUsuario()).thenReturn(useCaseAlterarSenha);
        doNothing().when(useCaseAlterarSenha).run(idUsuario, dto.senhaAntiga(), dto.senhaNova());

        mockMvc.perform(patch("/usuario/{id}/mudar-senha", idUsuario)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(dto)))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(factoryUseCase).alterarSenhaUsuario();
        verify(useCaseAlterarSenha).run(idUsuario, dto.senhaAntiga(), dto.senhaNova());
    }



    private String asJsonString(final Object obj) {
        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }
}
