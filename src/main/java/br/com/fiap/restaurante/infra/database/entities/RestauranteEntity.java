package br.com.fiap.restaurante.infra.database.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 *
 * CLASSE JPA
 * Utilizada para persistência no banco de dados.
 */
@Entity(name = "restaurante")
public class RestauranteEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String tipoCozinha;
    private String horarioFuncionamento;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Carrega o endereço apenas quando for acessado
    @JoinColumn(name = "endereco_id", unique = true)
    private EnderecoEntity endereco;

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = true)
    private UsuarioEntity dono;

    @OneToMany(mappedBy = "restaurante", fetch = FetchType.LAZY)
    private List<ItemCardapioEntity> itensCardapio;

    public RestauranteEntity() {
    }

    public RestauranteEntity(Long id, String nome, String tipoCozinha, String horarioFuncionamento, EnderecoEntity endereco, UsuarioEntity dono, List<ItemCardapioEntity> itensCardapio) {
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

    public EnderecoEntity getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoEntity endereco) {
        this.endereco = endereco;
    }

    public UsuarioEntity getDono() {
        return dono;
    }

    public void setDono(UsuarioEntity dono) {
        this.dono = dono;
    }

    public List<ItemCardapioEntity> getItensCardapio() {
        return itensCardapio;
    }

    public void setItensCardapio(List<ItemCardapioEntity> itensCardapio) {
        this.itensCardapio = itensCardapio;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RestauranteEntity that = (RestauranteEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
