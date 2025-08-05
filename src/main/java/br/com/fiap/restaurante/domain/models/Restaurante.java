package br.com.fiap.restaurante.domain.models;

import java.util.List;
import java.util.Objects;

public class Restaurante {

    private Long id;
    private String nome;
    private String tipoCozinha;
    private String horarioFuncionamento;

    private Endereco endereco;
    private Usuario dono;
    private List<ItemCardapio> itensCardapio;

    public Restaurante() {
    }

    public Restaurante(Long id) {
        this.id = id;
    }

    public Restaurante(  String nome, String tipoCozinha, String horarioFuncionamento,
                       Endereco endereco, Usuario dono) {
        this.nome = nome;
        this.tipoCozinha = tipoCozinha;
        this.horarioFuncionamento = horarioFuncionamento;
        this.endereco = endereco;
        this.dono = dono;
    }

    public Restaurante(Long id, String nome, String tipoCozinha, String horarioFuncionamento,
                       Endereco endereco, Usuario dono, List<ItemCardapio> itensCardapio) {
        this.id = id;
        this.nome = nome;
        this.tipoCozinha = tipoCozinha;
        this.horarioFuncionamento = horarioFuncionamento;
        this.endereco = endereco;
        this.dono = dono;
        this.itensCardapio = itensCardapio;
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

    public String getTipoCozinha() {
        return tipoCozinha;
    }

    public void setTipoCozinha(String tipoCozinha) {
        this.tipoCozinha = tipoCozinha;
    }

    public String getHorarioFuncionamento() {
        return horarioFuncionamento;
    }

    public void setHorarioFuncionamento(String horarioFuncionamento) {
        this.horarioFuncionamento = horarioFuncionamento;
    }

    public List<ItemCardapio> getItensCardapio() {
        return itensCardapio;
    }

    public void setItensCardapio(List<ItemCardapio> itensCardapio) {
        this.itensCardapio = itensCardapio;
    }

    public Usuario getDono() {
        return dono;
    }

    public void setDono(Usuario dono) {
        this.dono = dono;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Restaurante that = (Restaurante) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Restaurante{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", tipoCozinha='" + tipoCozinha + '\'' +
                ", horarioFuncionamento='" + horarioFuncionamento + '\'' +
                ", endereco=" + endereco +
                ", dono=" + dono +
                ", itensCardapio=" + itensCardapio +
                '}';
    }
}
