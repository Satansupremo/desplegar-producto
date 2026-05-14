package com.example.producto_oas.exception;

import com.example.producto_oas.dto.response.ServiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ServiceResponse<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> erroresMap = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String nombreCampo = ((FieldError) error).getField();
            String mensajeError = error.getDefaultMessage();
            erroresMap.put(nombreCampo, mensajeError);
        });

        ServiceResponse<Map<String, String>> response = new ServiceResponse<>(
                false,
                "Error de validacion en los datos enviados",
                erroresMap
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
