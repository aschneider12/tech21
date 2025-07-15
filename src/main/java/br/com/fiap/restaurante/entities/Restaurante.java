package br.com.fiap.restaurante.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "restaurante")
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String tipoCozinha;
    private String horarioFuncionamento;

    @OneToOne
    @JoinColumn(name = "endereco_id", nullable = true)
    private Endereco endereco;

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = true)
    private Usuario dono;

    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> itemsCardapio;
}
