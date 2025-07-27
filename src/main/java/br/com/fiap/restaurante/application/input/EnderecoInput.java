package br.com.fiap.restaurante.application.input;


import br.com.fiap.restaurante.domain.models.Endereco;

public record EnderecoInput(

        Long id,
        String rua,
        String numero,
        String cidade,
        String estado,
        String cep
) {

    public static Endereco toDomain(EnderecoInput e) {

        return new Endereco(e.id(), e.rua(), e.numero(), e.cidade(), e.estado(), e.cep());
    }

    public static EnderecoInput create(
            Long id,
            String rua,
            String numero,
            String cidade,
            String estado,
            String cep
    ){
        return new EnderecoInput(id, rua, numero, cidade, estado, cep);
    }
}