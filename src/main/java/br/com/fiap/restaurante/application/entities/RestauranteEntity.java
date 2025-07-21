package br.com.fiap.restaurante.application.entities;

import br.com.fiap.restaurante.core.dtos.restaurante.RestauranteCadastroDTO;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

/**
 *
 * CLASSE JPA
 * Utilizada para persistência no banco de dados.
 */
@Entity
@Table(name = "restaurante")
public class RestauranteEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String tipoCozinha;
    private String horarioFuncionamento;

    @OneToOne(fetch = FetchType.LAZY) // Carrega o endereço apenas quando for acessado
    @JoinColumn(name = "endereco_id", unique = true)
    private Endereco endereco;

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = true)
    private Usuario dono;

    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> itemsCardapio;

    public RestauranteEntity() {
    }

    public RestauranteEntity(RestauranteCadastroDTO dto) {
        this.nome = dto.nome();
        this.tipoCozinha = dto.tipoCozinha();
        this.horarioFuncionamento = dto.horarioFuncionamento();
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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Usuario getDono() {
        return dono;
    }

    public void setDono(Usuario dono) {
        this.dono = dono;
    }

    public List<Item> getItemsCardapio() {
        return itemsCardapio;
    }

    public void setItemsCardapio(List<Item> itemsCardapio) {
        this.itemsCardapio = itemsCardapio;
    }
}
