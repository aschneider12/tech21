package br.com.fiap.restaurante.domain.models;

import br.com.fiap.restaurante.application.exceptions.ValidationException;

import java.math.BigDecimal;
import java.util.Objects;

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

    /* Como nem sempre é possível ter um único construtor as validações
        são realizadas após o objeto ter sido criado, assim, pode ser chamado
        a qualquer momento após sua criação.
     *
     * @throws ValidationException
     */
    public void validacoesDominio() throws ValidationException {
        validarPreco();
        validarRestaurante();
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

    private void validarRestaurante() {
        if(this.restaurante == null || this.restaurante.getId() == null)
                throw new ValidationException("O item do cardápio obrigatoriamente deve pertencer a um restaurante!");
    }

    private void validarPreco() {
        if (this.preco != null)
            if (this.preco.compareTo(BigDecimal.ZERO) < 0)
                throw new ValidationException("Preço do produto não pode ser menor que zero!");
    }

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

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ItemCardapio that = (ItemCardapio) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ItemCardapio{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", tipoVenda='" + tipoVenda + '\'' +
                ", restaurante=" + restaurante +
                ", pathFoto='" + pathFoto + '\'' +
                '}';
    }
}
