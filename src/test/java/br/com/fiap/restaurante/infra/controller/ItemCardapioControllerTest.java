package br.com.fiap.restaurante.infra.controller;

import br.com.fiap.restaurante.application.input.ItemCardapioInput;
import br.com.fiap.restaurante.application.output.ItemCardapioOutput;
import br.com.fiap.restaurante.application.usecases.itemcardapio.*;
import br.com.fiap.restaurante.helper.Helper;
import br.com.fiap.restaurante.infra.controller.handlers.ExceptionController;
import br.com.fiap.restaurante.infra.dtos.ItemResponseDTO;
import br.com.fiap.restaurante.infra.mappers.ItemCardapioDTOMapper;
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
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ItemCardapioControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FactoryCardapioUseCase factoryUseCase;

    @Mock
    private UseCaseCadastrarItemCardapio useCaseCadastrarItemCardapio;

    @Mock
    private UseCaseAtualizarItemCardapio useCaseAtualizarItemCardapio;

    @Mock
    private UseCaseDeletarItemCardapio useCaseDeletarItemCardapio;

    @Mock
    private UseCaseBuscarItemCardapioPorID useCaseBuscarItemCardapioPorID;

    @Mock
    private UseCaseBuscarTodosItemCardapio useCaseBuscarTodosItemCardapio;


    AutoCloseable mock;

    @BeforeEach
    void setup(){
        mock = MockitoAnnotations.openMocks(this);
        ItemCardapioController itemCardapioController = new ItemCardapioController(factoryUseCase);
        mockMvc = MockMvcBuilders.standaloneSetup(itemCardapioController)
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
    void devePermtirBuscarItemCardapioPorId() throws Exception {
        var itemCardapio = Helper.gerarItemCardapio();
        Long restauranteId = 99L;
        Long itemId = 1L;
        itemCardapio.setId(itemId);
        itemCardapio.getRestaurante().setId(restauranteId);


        ItemCardapioOutput output = ItemCardapioOutput.fromDomain(itemCardapio);
        ItemResponseDTO itemCardapioDtoEsperado= ItemCardapioDTOMapper.INSTANCE.toDTO(output);

        when(factoryUseCase.buscarItemCardapioPorID()).thenReturn(useCaseBuscarItemCardapioPorID);
        when(useCaseBuscarItemCardapioPorID.run(restauranteId, itemId)).thenReturn(output);

        mockMvc.perform(get("/restaurante/{restauranteId}/cardapio/{id}", restauranteId, itemId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(itemCardapioDtoEsperado)));

        verify(factoryUseCase, times(1)).buscarItemCardapioPorID();
        verify(useCaseBuscarItemCardapioPorID, times(1)).run(restauranteId, itemId);
    }

    @Test
    void devePermtirBuscarTodosItensCardapioDeUmRestaurante() throws Exception {
        Long restauranteId = 99L;
        var itemCardapio1 = Helper.gerarItemCardapio();
        itemCardapio1.setId(1L);
        itemCardapio1.getRestaurante().setId(restauranteId);

        var itemCardapio2 = Helper.gerarItemCardapio();
        itemCardapio2.setId(2L);
        itemCardapio2.getRestaurante().setId(restauranteId);

        List<ItemCardapioOutput> outputs = List.of(
               ItemCardapioOutput.fromDomain(itemCardapio1),
               ItemCardapioOutput.fromDomain(itemCardapio2)
        );
        List<ItemResponseDTO> itensCardapioDtoEsperado = ItemCardapioDTOMapper.INSTANCE.toDTO(outputs);

        when(factoryUseCase.buscarTodosItemCardapio()).thenReturn(useCaseBuscarTodosItemCardapio);
        when(useCaseBuscarTodosItemCardapio.run(restauranteId)).thenReturn(outputs);

        mockMvc.perform(get("/restaurante/{restauranteId}/cardapio", restauranteId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(itensCardapioDtoEsperado)));

        verify(factoryUseCase, times(1)).buscarTodosItemCardapio();
        verify(useCaseBuscarTodosItemCardapio, times(1)).run(restauranteId);
    }

    @Test
    void devePermitirCadastrarItemCardapio() throws Exception {
        Long idRestaurante = 99L;
        var itemCardapio = Helper.gerarItemCardapio();
        itemCardapio.setId(1L);
        itemCardapio.getRestaurante().setId(idRestaurante);
        var itemDTO = Helper.gerarItemRequestDTO(itemCardapio);
        itemDTO.setRestauranteId(idRestaurante);


        ItemCardapioInput input = ItemCardapioDTOMapper.INSTANCE.toInputApplication(itemDTO);
        ItemCardapioOutput output = ItemCardapioOutput.fromDomain(itemCardapio);
        var itemCardapioDtoEsperado = ItemCardapioDTOMapper.INSTANCE.toDTO(output);



        when(factoryUseCase.cadastrarItemCardapio()).thenReturn(useCaseCadastrarItemCardapio);
        when(useCaseCadastrarItemCardapio.run(input)).thenReturn(output);

        System.out.println(input);

        mockMvc.perform(post("/restaurante/{restauranteId}/cardapio", idRestaurante)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(itemDTO)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(asJsonString(itemCardapioDtoEsperado)));

        verify(factoryUseCase, times(1)).cadastrarItemCardapio();
        verify(useCaseCadastrarItemCardapio, times(1)).run(input);
    }


    @Test
    void devePermtirAtualizarItemCardapio() throws Exception {
        Long idRestaurante = 99L;
        Long idItemCardapio = 1L;

        var itemCardapio = Helper.gerarItemCardapio();
        itemCardapio.setId(idItemCardapio);
        itemCardapio.getRestaurante().setId(idRestaurante);

        var itemDTO = Helper.gerarItemRequestDTO(itemCardapio);
        itemDTO.setRestauranteId(idRestaurante);

        ItemCardapioInput input = ItemCardapioDTOMapper.INSTANCE.toInputApplication(itemDTO);
        ItemCardapioOutput output = ItemCardapioOutput.fromDomain(itemCardapio);
        var itemCardapioDtoEsperado = ItemCardapioDTOMapper.INSTANCE.toDTO(output);

        when(factoryUseCase.atualizarItemCardapio()).thenReturn(useCaseAtualizarItemCardapio);
        when(useCaseAtualizarItemCardapio.run(idItemCardapio, input)).thenReturn(output);

        System.out.println(input);

        mockMvc.perform(put("/restaurante/{restauranteId}/cardapio/{id}", idRestaurante, idItemCardapio)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(itemDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(itemCardapioDtoEsperado)));

        verify(factoryUseCase, times(1)).atualizarItemCardapio();
        verify(useCaseAtualizarItemCardapio, times(1)).run(idItemCardapio, input);
    }

    @Test
    void devePermtirDeletarItemCardapi() throws Exception {
        Long idRestaurante = 99L;
        Long idItemCardapio = 1L;

        when(factoryUseCase.deletarItemCardapio()).thenReturn(useCaseDeletarItemCardapio);
        when(useCaseDeletarItemCardapio.run(idRestaurante, idItemCardapio)).thenReturn(true);

        mockMvc.perform(delete("/restaurante/{restauranteId}/cardapio/{id}", idRestaurante, idItemCardapio))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(factoryUseCase, times(1)).deletarItemCardapio();
        verify(useCaseDeletarItemCardapio, times(1)).run(idRestaurante, idItemCardapio);
    }

    @Test
    void deveRetornar400AoTentarDeletarItemCardapioInexistente() throws Exception {
        Long idRestaurante = 99L;
        Long idItemCardapio = 1L;

        when(factoryUseCase.deletarItemCardapio()).thenReturn(useCaseDeletarItemCardapio);
        when(useCaseDeletarItemCardapio.run(idRestaurante, idItemCardapio)).thenReturn(false);

        mockMvc.perform(delete("/restaurante/{restauranteId}/cardapio/{id}", idRestaurante, idItemCardapio))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string("{\"message\":\"Não foi possível apagar o item\"}"));

        verify(factoryUseCase).deletarItemCardapio();
        verify(useCaseDeletarItemCardapio).run(idRestaurante, idItemCardapio);
    }


    private String asJsonString(final Object obj) {
        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }
}
