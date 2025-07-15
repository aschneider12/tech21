package br.com.fiap.restaurante.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario_perfil")
public class UsuarioPerfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "tipo_usuario", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

}
