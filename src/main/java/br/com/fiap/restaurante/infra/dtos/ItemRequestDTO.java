package br.com.fiap.restaurante.infra.dtos;

import br.com.fiap.restaurante.infra.database.entities.TipoVenda;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.Objects;

public class ItemRequestDTO {

    @NotBlank(message = "O nome do item do cardápio não pode ser em branco!")
    String nome;

    String descricao;

    @DecimalMin(value = "0.00", inclusive = true, message = "Preço do produto não pode ser menor que zero!")
    BigDecimal preco;

    TipoVenda tipoVenda;

    Long restauranteId;

    String pathFoto;

    public ItemRequestDTO() {
    }

    public ItemRequestDTO(String nome, String descricao, BigDecimal preco, TipoVenda tipoVenda, String pathFoto) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.tipoVenda = tipoVenda;
        this.pathFoto = pathFoto;
    }

    public void setRestauranteId(Long restauranteId){
        this.restauranteId = restauranteId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public TipoVenda getTipoVenda() {
        return tipoVenda;
    }

    public void setTipoVenda(TipoVenda tipoVenda) {
        this.tipoVenda = tipoVenda;
    }

    public Long getRestauranteId() {
        return restauranteId;
    }

    public String getPathFoto() {
        return pathFoto;
    }

    public void setPathFoto(String pathFoto) {
        this.pathFoto = pathFoto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemRequestDTO that = (ItemRequestDTO) o;
        return Objects.equals(getNome(), that.getNome()) && Objects.equals(getRestauranteId(), that.getRestauranteId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNome(), getRestauranteId());
    }
}
