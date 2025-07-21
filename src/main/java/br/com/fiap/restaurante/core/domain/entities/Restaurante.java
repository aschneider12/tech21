package br.com.fiap.restaurante.core.domain.entities;

/**
 * CONCRETA
 * Entidade para persistência, independe de banco de dados ou local que vai ser persistida.
 * Essa entidade representa o dado em si, conforme o dominio.
 */
public class Restaurante {

    private Long id;
    private String nome;
    private String tipoCozinha;
    private String horarioFuncionamento;

//    private Endereco endereco;
//    private Usuario dono;
//    private List<Item> itemsCardapio;

    private Restaurante(Long id, String nome, String tipoCozinha, String horarioFuncionamento) {
        this.id = id;
        this.nome = nome;
        this.tipoCozinha = tipoCozinha;
        this.horarioFuncionamento = horarioFuncionamento;
    }

    public static Restaurante create(
            String nome,
            String tipoCozinha,
            String horarioFuncionamento
    ){

        //realizar validações necessárias
        return new Restaurante(null, nome, tipoCozinha,horarioFuncionamento);

    }
    public static Restaurante create(
            Long id,
            String nome,
            String tipoCozinha,
            String horarioFuncionamento
    ){

        //realizar validações necessárias
      return new Restaurante(id,nome, tipoCozinha,horarioFuncionamento);
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
}
