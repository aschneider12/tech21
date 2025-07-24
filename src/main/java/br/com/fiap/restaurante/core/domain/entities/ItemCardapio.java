package br.com.fiap.restaurante.core.domain.entities;

import java.math.BigDecimal;

public class ItemCardapio {

    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private String tipoVenda;
    private String pathFoto;

    // Construtor protegido para frameworks ou fábricas
    protected ItemCardapio() {}

    private ItemCardapio(Long id, String nome, String descricao, BigDecimal preco, String tipoVenda, String pathFoto) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.tipoVenda = tipoVenda;
        this.pathFoto = pathFoto;
    }

    public static ItemCardapio create(String nome, String descricao, BigDecimal preco, String tipoVenda, String pathFoto) {
        return new ItemCardapio(null, nome, descricao, preco, tipoVenda, pathFoto);
    }

    public static ItemCardapio create(Long id, String nome, String descricao, BigDecimal preco, String tipoVenda, String pathFoto) {
        return new ItemCardapio(id, nome, descricao, preco, tipoVenda, pathFoto);
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public BigDecimal getPreco() { return preco; }
    public String getTipoVenda() { return tipoVenda; }
    public String getPathFoto() { return pathFoto; }

    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }
    public void setTipoVenda(String tipoVenda) { this.tipoVenda = tipoVenda; }
    public void setPathFoto(String pathFoto) { this.pathFoto = pathFoto; }
}

