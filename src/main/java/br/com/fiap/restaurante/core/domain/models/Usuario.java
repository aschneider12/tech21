package br.com.fiap.restaurante.core.domain.models;

import br.com.fiap.restaurante.application.dtos.usuario.UsuarioInputDTO;
import br.com.fiap.restaurante.application.dtos.usuario.UsuarioOutputDTO;

import java.time.LocalDateTime;
import java.util.List;

public class Usuario {

    private Long id;
    private String nome;
    private String email;
    private String login;
    private String senha;
    private LocalDateTime dataUltimaAlteracao;
    private Endereco endereco;
    private List<String> perfis;

    public Usuario(Long id) {
        this.id = id;
    }

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

    //errado pra kct
    public static Usuario fromDTO(UsuarioInputDTO dto) {

       return new Usuario(dto.nome(),dto.email(),dto.login(),dto.senha(), LocalDateTime.now());
    }

    //viola a regra do dominio nao depender de externo, depende do DTO
    public static Usuario fromDTO(UsuarioOutputDTO dto) {

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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<String> getPerfis() {
        return perfis;
    }

    public void setPerfis(List<String> perfis) {
        this.perfis = perfis;
    }
}
