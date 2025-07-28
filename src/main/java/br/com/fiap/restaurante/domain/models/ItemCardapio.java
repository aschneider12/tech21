package br.com.fiap.restaurante.domain.models;

import java.math.BigDecimal;

public class ItemCardapio {

    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;

    private String tipoVenda;
    private Restaurante restaurante;
    private String pathFoto;

    public ItemCardapio() {
    }

    private ItemCardapio(Long id, String nome, String descricao, BigDecimal preco, String tipoVenda,
                         Restaurante restaurante, String pathFoto) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.tipoVenda = tipoVenda;
        this.restaurante = restaurante;
        this.pathFoto  = pathFoto;

    }

    //validar preço
    //validar se pertence a algum restaurante

    public static ItemCardapio create(
            String nome,
            String descricao,
            BigDecimal preco,
            String tipoVenda,
            Restaurante restaurante,
            String pathFoto
    ) {
        return new ItemCardapio(null, nome, descricao, preco, tipoVenda, restaurante, pathFoto);
    }


    public static ItemCardapio create(
            Long id,
            String nome,
            String descricao,
            BigDecimal preco,
            String tipoVenda,
            Restaurante restaurante,
            String pathFoto
    ) {
        return new ItemCardapio(id, nome, descricao, preco, tipoVenda, restaurante, pathFoto);
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getTipoVenda() {
        return tipoVenda;
    }

    public void setTipoVenda(String tipoVenda) {
        this.tipoVenda = tipoVenda;
    }

    public String getPathFoto() {
        return pathFoto;
    }

    public void setPathFoto(String pathFoto) {
        this.pathFoto = pathFoto;
    }

//    public Restaurante getRestaurante() {
//        return restaurante;
//    }
//
//    public void setRestaurante(Restaurante restaurante) {
//        this.restaurante = restaurante;
//    }
}
