package com.example.producto_oas.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductoResponse {

    private int idProducto;
    private String nombre;
    private String descripcion;
    private int stock;
    private double precio;
}
