package br.com.fiap.restaurante.application.output;


import br.com.fiap.restaurante.domain.models.Endereco;

public record EnderecoOutput (

        Long id,
        String rua,
        String numero,
        String cidade,
        String estado,
        String cep
) {

    public static EnderecoOutput fromDomain(Endereco e) {

        return new EnderecoOutput(e.getId(), e.getRua(), e.getNumero(),
                e.getCidade(), e.getEstado(), e.getEstado());
    }

    public static EnderecoOutput create(
            Long id,
            String rua,
            String numero,
            String cidade,
            String estado,
            String cep
    ){
        return new EnderecoOutput(id, rua, numero, cidade, estado, cep);
    }
}