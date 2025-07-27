package br.com.fiap.restaurante.infra.database.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity(name = "usuario_perfil")
public class UsuarioPerfil implements Serializable {

    @Id
    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Id
    @Column(name = "tipo_usuario", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", insertable = false, updatable = false)
    @JsonBackReference //json irá ignorar esse lado da referência, evita ref circular inifinita
    private UsuarioEntity usuario;

    public UsuarioPerfil() {

    }

    public UsuarioPerfil(UsuarioEntity usuarioEntity, TipoUsuario tipoUsuario) {
        this.usuarioId = usuarioEntity.getId();
        this.usuario = usuarioEntity;
        this.tipoUsuario = tipoUsuario;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuarioEntity) {
        this.usuario = usuarioEntity;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioPerfil that = (UsuarioPerfil) o;
        return Objects.equals(usuarioId, that.usuarioId) && tipoUsuario == that.tipoUsuario;
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioId, tipoUsuario);
    }
}
