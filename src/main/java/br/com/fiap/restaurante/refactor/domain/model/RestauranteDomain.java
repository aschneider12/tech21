package br.com.fiap.restaurante.refactor.domain.model;

public class RestauranteDomain {

    private Long id;
    private String nome;

    private String tipoCozinha;

    private String horarioFuncionamento;

    private Long dono;

    //private EnderecoDomain endereco;

    public RestauranteDomain(){}

    public RestauranteDomain(Long id, String nome, String tipoCozinha, String horarioFuncionamento, Long dono) {
        this.id = id;
        this.nome = nome;
        this.tipoCozinha = tipoCozinha;
        this.horarioFuncionamento = horarioFuncionamento;
        this.dono = dono;
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

    public Long getDono() {
        return dono;
    }

    public void setDono(Long dono) {
        this.dono = dono;
    }
}
