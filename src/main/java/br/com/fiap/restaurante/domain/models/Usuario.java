package br.com.fiap.restaurante.domain.models;

import br.com.fiap.restaurante.application.exceptions.ValidationException;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;

public class Usuario {

    private Long id;
    private String nome;
    private String email;
    private String login;
    private String senha;
    private LocalDateTime dataUltimaAlteracao;
    private Endereco endereco;
    private Set<String> perfis;

    public Usuario() {
    }

    public Usuario(Long id) {
        this.id = id;
    }

    public void validacoesDominio() throws ValidationException {
        validarSenhaForte();
        validarEmail();
        validarCaracteresLogin();
        removerCaracteresEspeciaisNome();
    }

    private void removerCaracteresEspeciaisNome() {
        nome = nome.trim();
    }

    private void validarCaracteresLogin() {
        login = login.trim();
    }

    private static final Pattern EMAIL_REGEX = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$",
            Pattern.CASE_INSENSITIVE
    );

    private void validarEmail() {
        if (email == null || !EMAIL_REGEX.matcher(email).matches()) {
            throw new IllegalArgumentException("E-mail inválido");
        }
    }

    private void validarSenhaForte() {
        if(senha == null)
            throw new IllegalArgumentException("Senha não pode ser nula!");
        if (!senha.matches(".*[A-Z].*"))
            throw new IllegalArgumentException("Senha deve conter pelo menos uma letra maiúscula!");
        if (!senha.matches(".*\\d.*"))
            throw new IllegalArgumentException("Senha deve conter pelo menos um número.");
        if (!senha.matches(".*[^a-zA-Z0-9].*"))
            throw new IllegalArgumentException("Senha deve conter pelo menos um caracter especial.");
        if (senha.length() < 8)
            throw new IllegalArgumentException("Senha deve ter no mínimo 8 caracteres.");
    }

    public Usuario(Long id, String nome, String email, String login,
                   String senha,
                   Endereco endereco, Set<String> perfis) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.endereco = endereco;
        this.perfis = perfis;
    }
    public static Usuario create(
            Long id, String nome, String email, String login,
            String senha, Endereco endereco, Set<String> perfis
    ) {
        return new Usuario(
                id,  nome, email, login,
                senha,
                endereco, perfis);
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

    public Set<String> getPerfis() {
        return perfis;
    }

    public void setPerfis(Set<String> perfis) {
        this.perfis = perfis;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
