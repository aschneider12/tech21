package br.com.fiap.restaurante.infra.controller;

import br.com.fiap.restaurante.application.input.RestauranteInput;
import br.com.fiap.restaurante.application.output.RestauranteOutput;
import br.com.fiap.restaurante.application.usecases.restaurante.*;
import br.com.fiap.restaurante.application.usecases.usuario.FactoryUsuarioUseCase;
import br.com.fiap.restaurante.application.usecases.usuario.perfil.UseCaseAdicionarPerfisUsuario;
import br.com.fiap.restaurante.application.usecases.usuario.perfil.UseCaseBuscarPerfisUsuario;
import br.com.fiap.restaurante.application.usecases.usuario.perfil.UseCaseRemoverPerfisUsuario;
import br.com.fiap.restaurante.helper.Helper;
import br.com.fiap.restaurante.infra.controller.handlers.ExceptionController;
import br.com.fiap.restaurante.infra.database.entities.TipoUsuario;
import br.com.fiap.restaurante.infra.dtos.PerfilRequestDTO;
import br.com.fiap.restaurante.infra.dtos.PerfilResponseDTO;
import br.com.fiap.restaurante.infra.dtos.RestauranteDTO;
import br.com.fiap.restaurante.infra.mappers.RestauranteDTOMapper;
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
import java.util.Set;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PerfilControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FactoryUsuarioUseCase factoryUseCase;

    @Mock
    private UseCaseAdicionarPerfisUsuario useCaseAdicionarPerfisUsuario;

    @Mock
    private UseCaseRemoverPerfisUsuario useCaseRemoverPerfisUsuario;

    @Mock
    private UseCaseBuscarPerfisUsuario useCaseBuscarPerfisUsuario;


    AutoCloseable mock;

    @BeforeEach
    void setUp() {
        mock = MockitoAnnotations.openMocks(this);
        PerfilController controller = new PerfilController(factoryUseCase);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ExceptionController())
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }, "/*")
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }


    @Test
    void devePermtirBuscarPerfisDoUsuario() throws Exception {
        Long idUsuario = 42L;
        Set<String> perfis = Set.of("DONO", "ADMINISTRADOR");
        List<TipoUsuario> perfisEnum = perfis.stream().map(TipoUsuario::valueOf).toList();

        PerfilResponseDTO perfisDtoEsperado = new PerfilResponseDTO("ID - " + idUsuario, perfisEnum);

        when(factoryUseCase.buscarPerfisUsuario()).thenReturn(useCaseBuscarPerfisUsuario);
        when(useCaseBuscarPerfisUsuario.run(idUsuario)).thenReturn(perfis);

        mockMvc.perform(get("/usuario/{usuarioId}/perfil", idUsuario))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(perfisDtoEsperado)));

        verify(factoryUseCase, times(1)).buscarPerfisUsuario();
        verify(useCaseBuscarPerfisUsuario, times(1)).run(idUsuario);
    }

    @Test
    void devePermitirAdicionarPerfisParaUsuario() throws Exception {
        Long idUsuario = 42L;
        PerfilRequestDTO perfisDto = new PerfilRequestDTO(List.of(TipoUsuario.CLIENTE));
        Set<String> perfisString = perfisDto.perfis().stream().map(Enum::name).collect(Collectors.toSet());

        when(factoryUseCase.adicionarPerfisUsuario()).thenReturn(useCaseAdicionarPerfisUsuario);
        doNothing().when(useCaseAdicionarPerfisUsuario).run(idUsuario, perfisString);

        mockMvc.perform(post("/usuario/{usuarioId}/perfil", idUsuario)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(perfisDto)))
                .andDo(print())
                .andExpect(status().isCreated());

        verify(factoryUseCase, times(1)).adicionarPerfisUsuario();
        verify(useCaseAdicionarPerfisUsuario, times(1)).run(idUsuario, perfisString);
    }

    @Test
    void deveRemoverPerfisDoUsuario() throws Exception {
        Long usuarioId = 42L;
        PerfilRequestDTO perfisDto = new PerfilRequestDTO(List.of(TipoUsuario.CLIENTE));
        Set<String> perfisString = perfisDto.perfis().stream().map(Enum::name).collect(Collectors.toSet());

        when(factoryUseCase.removerPerfisUsuario()).thenReturn(useCaseRemoverPerfisUsuario);
        doNothing().when(useCaseRemoverPerfisUsuario).run(usuarioId, perfisString);

        mockMvc.perform(delete("/usuario/{usuarioId}/perfil", usuarioId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(perfisDto)))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(factoryUseCase, times(1)).removerPerfisUsuario();
        verify(useCaseRemoverPerfisUsuario, times(1)).run(usuarioId, perfisString);
    }


    private String asJsonString(final Object obj) {
        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }

}
