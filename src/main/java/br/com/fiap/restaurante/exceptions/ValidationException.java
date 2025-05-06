package br.com.fiap.restaurante.exceptions;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ValidationException extends Exception {

    public ValidationException(String message) {
        super(message);
    }
}
