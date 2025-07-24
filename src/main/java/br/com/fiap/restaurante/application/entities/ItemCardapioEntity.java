package br.com.fiap.restaurante.application.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "itens_cardapio")
public class ItemCardapioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private BigDecimal preco;
    private String tipoVenda;
    private String pathFoto;

    // Relacionamento opcional com restaurante, se existir
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurante_id")
    private RestauranteEntity restaurante;

    public ItemCardapioEntity() {}

    public ItemCardapioEntity(Long id, String nome, String descricao, BigDecimal preco, String tipoVenda, String pathFoto) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.tipoVenda = tipoVenda;
        this.pathFoto = pathFoto;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }

    public String getTipoVenda() { return tipoVenda; }
    public void setTipoVenda(String tipoVenda) { this.tipoVenda = tipoVenda; }

    public String getPathFoto() { return pathFoto; }
    public void setPathFoto(String pathFoto) { this.pathFoto = pathFoto; }

    public RestauranteEntity getRestaurante() { return restaurante; }
    public void setRestaurante(RestauranteEntity restaurante) { this.restaurante = restaurante; }
}
