package br.com.fiap.restaurante.infra.dtos;

public record EnderecoDTO(

         Long id,
         String rua,
         String numero,
         String cidade,
         String estado,
         String cep
) {

    public static EnderecoDTO create(
            Long id,
            String rua,
            String numero,
            String cidade,
            String estado,
            String cep
    ){
        return new EnderecoDTO(id, rua, numero, cidade, estado, cep);
    }
}
