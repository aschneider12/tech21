package br.com.fiap.restaurante.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestauranteApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestauranteApplication.class, args);
    }

    // Removido o código inseguro de geração de senha
    // PasswordEncoder encoder = new BCryptPasswordEncoder();
    // System.out.println("Senha criptografada: " + encoder.encode("123456"));
}
