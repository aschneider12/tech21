package br.com.fiap.restaurante.exceptions;



public class ErrorResponse {

    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

    // Getter e Setter (ou use Lombok @Getter/@Setter)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
