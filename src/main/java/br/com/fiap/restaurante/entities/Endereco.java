package br.com.fiap.restaurante.entities;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.Embeddable;

// TODO - será migrada de String para entidade nas próximas fases.
@Hidden
@Embeddable
public class Endereco {
    private String rua;
    private String numero;
    private String cidade;
    private String estado;
    private String cep;
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}

}
