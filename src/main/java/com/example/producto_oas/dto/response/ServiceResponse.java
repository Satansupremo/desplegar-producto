package com.example.producto_oas.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResponse<T> {

    private boolean ok;
    private String mensaje;
    private T datos;

    public static <T> ServiceResponse<T> exito(String mensaje, T datos) {
        return new ServiceResponse<>(true, mensaje, datos);
    }

    public static <T> ServiceResponse<T> error(String mensaje) {
        return new ServiceResponse<>(false, mensaje, null);
    }
}
