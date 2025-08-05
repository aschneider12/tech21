package br.com.fiap.restaurante.infra.database.repositories.adapter;

import br.com.fiap.restaurante.domain.models.ItemCardapio;
import br.com.fiap.restaurante.domain.models.Restaurante;
import br.com.fiap.restaurante.helper.Helper;
import br.com.fiap.restaurante.infra.database.entities.ItemCardapioEntity;
import br.com.fiap.restaurante.infra.database.entities.RestauranteEntity;
import br.com.fiap.restaurante.infra.database.mappers.ItemCardapioEntityMapper;
import br.com.fiap.restaurante.infra.database.mappers.RestauranteEntityMapper;
import br.com.fiap.restaurante.infra.database.repositories.jpa.ItemRepository;
import br.com.fiap.restaurante.infra.database.repositories.jpa.RestauranteRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
//@AutoConfigureTestDatabase
@Transactional
@Sql(scripts = {"/db_restaurante_clean.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Sql(scripts = {"/db_restaurante_load.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class ItemCardapioRepositoryAdapterIT {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private ItemCardapioRepositoryAdapter adapter;



    @Test
    void devePermtirCadastrarItemCardapio(){


        RestauranteEntity restauranteEntity = restauranteRepository.findById(9999L)
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));

        Restaurante restaurante = RestauranteEntityMapper.INSTANCE.toDomain(restauranteEntity);

        var itemCardapio = Helper.gerarItemCardapio();
        itemCardapio.setRestaurante(restaurante);
        itemCardapio.setNome("item teste para cadastro");


        var itemCardapioCadastrado = adapter.cadastrar(itemCardapio);

        assertThat(itemCardapioCadastrado)
                .isNotNull()
                .isInstanceOf(ItemCardapio.class);

        assertThat(itemCardapioCadastrado.getNome())
                .isEqualTo(itemCardapio.getNome());

        assertThat(itemCardapioCadastrado.getDescricao())
                .isEqualTo(itemCardapio.getDescricao());

        assertThat(itemCardapioCadastrado.getTipoVenda())
                .isEqualTo(itemCardapio.getTipoVenda());

    }


    @Test
    void devePermtirAtualizarItemCardapio() {

        ItemCardapioEntity itemCardapioEntity = itemRepository.findById(9999L)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        ItemCardapio itemCardapioExistente = ItemCardapioEntityMapper.INSTANCE.toDomain(itemCardapioEntity);


        String novoNome = "Nome Atualizado";
        BigDecimal novoPreco = BigDecimal.valueOf(30);
        itemCardapioExistente.setNome(novoNome);
        itemCardapioExistente.setPreco(novoPreco);


        var itemCardapioAtualizado = adapter.atualizar(itemCardapioExistente);


        assertThat(itemCardapioAtualizado)
                .isNotNull();

        assertThat(itemCardapioAtualizado.getId())
                .isEqualTo(itemCardapioExistente.getId());

        assertThat(itemCardapioAtualizado.getNome())
                .isEqualTo(novoNome);

        assertThat(itemCardapioAtualizado.getPreco())
                .isEqualTo(novoPreco);
    }

    @Test
    void devePermitirDeletarItemCardapio(){
        Long id = 9999L;

        var itemCardapioFoiDeletado = adapter.deletar(id);

        assertThat(itemCardapioFoiDeletado)
                .isTrue();
    }

    @Test
    void devePermtirBuscarTodosItensCardapio() {
        List<ItemCardapio> itensCardapio = adapter.buscarTodosItensCardapio(9999L);

        assertThat(itensCardapio)
                .isNotNull();
        assertThat(itensCardapio)
                .isNotEmpty();
    }

    @Test
    void devePermitirBuscarRestaurantePorId(){
        Long id = 9999L;

        var itemCardapioEncontrado = adapter.buscarItemCardapioPorIdentificador(id);

        assertThat(itemCardapioEncontrado)
                .isNotNull()
                .isInstanceOf(ItemCardapio.class);
    }

    @Test
    void devePermitirBuscarRestaurantePorNome(){
        String nome = "item teste";

        var itemCardapioEncontrado = adapter.buscarItemCardapioPorNome(nome);

        assertThat(itemCardapioEncontrado)
                .isNotNull()
                .isInstanceOf(ItemCardapio.class);
    }
}
