package br.com.fiap.restaurante.core.domain.entities;

import java.math.BigDecimal;

public class ItemCardapio {

    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;

    private String tipoVenda;
    private Restaurante restaurante;
    private String pathFoto;
}
