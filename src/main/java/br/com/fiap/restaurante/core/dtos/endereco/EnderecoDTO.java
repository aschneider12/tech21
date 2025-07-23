package br.com.fiap.restaurante.core.dtos.endereco;

public record EnderecoDTO(

         Long id,
         String rua,
         String numero,
         String cidade,
         String estado,
         String cep
) {
}
