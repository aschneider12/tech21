package br.com.fiap.restaurante.controller.handlers;

import br.com.fiap.restaurante.DTO.SenhaIncorretaDTO;
import br.com.fiap.restaurante.exceptions.SenhaIncorretaException;
import br.com.fiap.restaurante.exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ValidationException.class)
    public void handlePersonalValidation(){
        //validacao personalizada se houver
    }

    //TODO - retornar uma lista de error response, padronizada
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>>
    handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<FieldError> errors = ex.getBindingResult().getFieldErrors();
        Map<String, List<String>> errorMap = new HashMap<>();

        errors.forEach(error -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errorMap.computeIfAbsent(fieldName, k -> new java.util.ArrayList<>()).add(errorMessage);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
    }

    @ExceptionHandler(SenhaIncorretaException.class)
    public ResponseEntity<SenhaIncorretaDTO> handleSenhaIncorretaException(SenhaIncorretaException e){
        var status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(new SenhaIncorretaDTO(status.value(), e.getMessage()));

    }
}
