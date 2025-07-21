package br.com.fiap.restaurante.core.domain.entities;

import br.com.fiap.restaurante.core.domain.usecases.usuario.UseCaseCadastrarUsuario;
import br.com.fiap.restaurante.core.dtos.usuario.UsuarioCadastroDTO;
import br.com.fiap.restaurante.core.dtos.usuario.UsuarioRetornoDTO;

import java.time.LocalDateTime;
/*
CLASSE CONCRETA DA ENTIDADE
 */
public class Usuario {

    private Long id;
    private String nome;
    private String email;
    private String login;
    private String senha;
    private LocalDateTime dataUltimaAlteracao;
//    private Endereco endereco;
//    private List<UsuarioPerfil> perfis;

    public Usuario(Long id, String nome, String email, String login, String senha, LocalDateTime dataUltimaAlteracao) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.dataUltimaAlteracao = dataUltimaAlteracao;
    }

    public Usuario(String nome, String email, String login, String senha, LocalDateTime dataUltimaAlteracao) {
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.dataUltimaAlteracao = dataUltimaAlteracao;
    }

    public static Usuario fromDTO(UsuarioCadastroDTO dto) {

       return new Usuario(dto.nome(),dto.email(),dto.login(),dto.senha(), LocalDateTime.now());
    }
    public static Usuario fromDTO(UsuarioRetornoDTO dto) {

        return new Usuario(dto.nome(),dto.email(),dto.login(),dto.senha(), LocalDateTime.now());
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDateTime getDataUltimaAlteracao() {
        return dataUltimaAlteracao;
    }

    public void setDataUltimaAlteracao(LocalDateTime dataUltimaAlteracao) {
        this.dataUltimaAlteracao = dataUltimaAlteracao;
    }

//    public Endereco getEndereco() {
//        return endereco;
//    }
//
//    public void setEndereco(Endereco endereco) {
//        this.endereco = endereco;
//    }
//
//    public List<UsuarioPerfil> getPerfis() {
//        return perfis;
//    }
//
//    public void setPerfis(List<UsuarioPerfil> perfis) {
//        this.perfis = perfis;
//    }
}
