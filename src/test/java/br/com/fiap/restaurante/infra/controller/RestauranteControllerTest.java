package br.com.fiap.restaurante.infra.controller;

import br.com.fiap.restaurante.application.input.RestauranteInput;
import br.com.fiap.restaurante.application.output.RestauranteOutput;
import br.com.fiap.restaurante.application.usecases.restaurante.*;
import br.com.fiap.restaurante.helper.Helper;
import br.com.fiap.restaurante.infra.controller.handlers.ExceptionController;
import br.com.fiap.restaurante.infra.dtos.EnderecoDTO;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class RestauranteControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FactoryRestauranteUseCase factoryUseCase;

    @Mock
    private UseCaseCadastrarRestaurante useCaseCadastrarRestaurante;

    @Mock
    private UseCaseAtualizarRestaurante useCaseAtualizarRestaurante;

    @Mock
    private UseCaseDeletarRestaurante useCaseDeletarRestaurante;

    @Mock
    private UseCaseBuscarRestaurantePorID useCaseBuscarRestaurantePorID;

    @Mock
    private UseCaseBuscarTodosRestaurantes useCaseBuscarTodosRestaurantes;


    AutoCloseable mock;

    @BeforeEach
    void setup(){
        mock = MockitoAnnotations.openMocks(this);
        RestauranteController restauranteController = new RestauranteController(factoryUseCase);
        mockMvc = MockMvcBuilders.standaloneSetup(restauranteController)
                .setControllerAdvice(new ExceptionController())
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }, "/*")
                .build();
    }

    @AfterEach
    void teardown() throws Exception{
        mock.close();
    }


    @Test
    void devePermitirCadastrarRestaurante() throws Exception {

        var restaurante = Helper.gerarRestaurante();
        restaurante.setId(1L);
        restaurante.getDono().setId(99L);
        RestauranteDTO restauranteResquest = Helper.gerarRestauranteDTO(restaurante);
        RestauranteInput input = RestauranteDTOMapper.INSTANCE.toInputApplication(restauranteResquest);
        RestauranteOutput output = RestauranteOutput.fromDomain(restaurante);

        when(factoryUseCase.cadastrarRestaurante()).thenReturn(useCaseCadastrarRestaurante);
        when(useCaseCadastrarRestaurante.run(input)).thenReturn(output);


        mockMvc.perform(post("/restaurante")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(restauranteResquest))
                )
                .andDo(print())
                .andExpect(status().isCreated());
        verify(factoryUseCase, times(1)).cadastrarRestaurante();
        verify(useCaseCadastrarRestaurante, times(1)).run(input);

    }



    @Test
    void devePermitirAtualizarRestaurante() throws Exception {

        Long idRestaurante = 1L;
        var restaurante = Helper.gerarRestaurante();
        restaurante.setId(idRestaurante);
        restaurante.getDono().setId(99L);

        RestauranteDTO restauranteRequest = Helper.gerarRestauranteDTO(restaurante);
        RestauranteInput input = RestauranteDTOMapper.INSTANCE.toInputApplication(restauranteRequest);
        RestauranteOutput output = RestauranteOutput.fromDomain(restaurante);

        when(factoryUseCase.atualizarRestaurante()).thenReturn(useCaseAtualizarRestaurante);
        when(useCaseAtualizarRestaurante.run(input, idRestaurante)).thenReturn(output);


        mockMvc.perform(put("/restaurante/{id}", idRestaurante)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(restauranteRequest)))
                .andDo(print())
                .andExpect(status().isOk());


        verify(factoryUseCase, times(1)).atualizarRestaurante();
        verify(useCaseAtualizarRestaurante, times(1)).run(input, idRestaurante);
    }


    @Test
    void devePermtirDeletarRestaurante() throws Exception {

        Long idRestaurante = 1L;

        when(factoryUseCase.deletarRestaurante()).thenReturn(useCaseDeletarRestaurante);
        when(useCaseDeletarRestaurante.run(idRestaurante)).thenReturn(true);

        mockMvc.perform(delete("/restaurante/{id}", idRestaurante))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andExpect(content().string("Restaurante deletado!"));


        verify(factoryUseCase, times(1)).deletarRestaurante();
        verify(useCaseDeletarRestaurante, times(1)).run(idRestaurante);
    }


    @Test
    void deveRetornar404AoTentarDeletarRestauranteCujoIDNaoFoiEncontrado() throws Exception {

        Long idRestaurante = 1L;

        when(factoryUseCase.deletarRestaurante()).thenReturn(useCaseDeletarRestaurante);
        when(useCaseDeletarRestaurante.run(idRestaurante)).thenReturn(false);

        mockMvc.perform(delete("/restaurante/{id}", idRestaurante))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string("Não foi deletado!"));


        verify(factoryUseCase, times(1)).deletarRestaurante();
        verify(useCaseDeletarRestaurante, times(1)).run(idRestaurante);
    }


    @Test
    void devePermtirBuscarRestaurantePorId() throws Exception {

        Long idRestaurante = 1L;

        var restaurante = Helper.gerarRestaurante();
        restaurante.setId(idRestaurante);
        restaurante.getDono().setId(99L);

        RestauranteOutput output = RestauranteOutput.fromDomain(restaurante);
        RestauranteDTO dtoEsperado = RestauranteDTOMapper.INSTANCE.toDTO(output);

        when(factoryUseCase.buscarRestaurantePorId()).thenReturn(useCaseBuscarRestaurantePorID);
        when(useCaseBuscarRestaurantePorID.run(idRestaurante)).thenReturn(output);


        mockMvc.perform(get("/restaurante/{id}", idRestaurante)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(dtoEsperado)));


        verify(factoryUseCase, times(1)).buscarRestaurantePorId();
        verify(useCaseBuscarRestaurantePorID, times(1)).run(idRestaurante);
    }

    @Test
    void devePermtirBuscarTodosOsRestaurantes() throws Exception {

        var restaurante1 = Helper.gerarRestaurante();
        restaurante1.setId(1L);
        restaurante1.getDono().setId(99L);

        var restaurante2 = Helper.gerarRestaurante();
        restaurante2.setId(2L);
        restaurante2.getDono().setId(100L);

        List<RestauranteOutput> outputs = List.of(
                RestauranteOutput.fromDomain(restaurante1),
                RestauranteOutput.fromDomain(restaurante2)
        );

        List<RestauranteDTO> restaurantesDtoEsperados = RestauranteDTOMapper.INSTANCE.toDTO(outputs);

        when(factoryUseCase.buscarTodosRestaurantes()).thenReturn(useCaseBuscarTodosRestaurantes);
        when(useCaseBuscarTodosRestaurantes.run()).thenReturn(outputs);


        mockMvc.perform(get("/restaurante")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(restaurantesDtoEsperados)));


        verify(factoryUseCase, times(1)).buscarTodosRestaurantes();
        verify(useCaseBuscarTodosRestaurantes, times(1)).run();
    }



    private String asJsonString(final Object obj) {
        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }

}
