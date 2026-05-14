package com.example.producto_oas.service;

import com.example.producto_oas.dto.request.ProductoRequest;
import com.example.producto_oas.dto.response.ProductoResponse;
import com.example.producto_oas.dto.response.ServiceResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductoService {

    ServiceResponse<List<ProductoResponse>> listarProductos();
    ServiceResponse<Integer> registrarProducto(ProductoRequest productoRequest);
    ServiceResponse<ProductoResponse> obtenerProductoPorId(int idProducto);
}
