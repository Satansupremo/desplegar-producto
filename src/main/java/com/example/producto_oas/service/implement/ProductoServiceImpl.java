package com.example.producto_oas.service.implement;

import com.example.producto_oas.dto.request.ProductoRequest;
import com.example.producto_oas.dto.response.ProductoResponse;
import com.example.producto_oas.dto.response.ServiceResponse;
import com.example.producto_oas.entity.ProductoEntity;
import com.example.producto_oas.persistence.ProductoRepository;
import com.example.producto_oas.service.ProductoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public ServiceResponse<List<ProductoResponse>> listarProductos() {
        try {
            List<ProductoEntity> entidades = productoRepository.findAll();
            List<ProductoResponse> listaDto = new ArrayList<>();

            for (ProductoEntity entidad : entidades) {
                ProductoResponse response = new ProductoResponse();
                response.setIdProducto(entidad.getIdProducto());
                response.setNombre(entidad.getNombre());
                response.setDescripcion(entidad.getDescripcion());
                response.setStock(entidad.getStock());
                response.setPrecio(entidad.getPrecio());
                listaDto.add(response);
            }

            return ServiceResponse.exito("Listado de productos obtenido con éxito", listaDto);

        } catch (Exception e) {
            return ServiceResponse.error("Error al listar productos: " + e.getMessage());
        }
    }

    @Override
    public ServiceResponse<Integer> registrarProducto(ProductoRequest productoRequest) {
        try {
            ProductoEntity entidad = new ProductoEntity();
            entidad.setNombre(productoRequest.getNombre());
            entidad.setDescripcion(productoRequest.getDescripcion());
            entidad.setStock(productoRequest.getStock());
            entidad.setPrecio(productoRequest.getPrecio());

            ProductoEntity entidadGuardada = productoRepository.save(entidad);

            return ServiceResponse.exito("Producto registrado correctamente", entidadGuardada.getIdProducto());

        } catch (Exception e) {
            return ServiceResponse.error("Error al registrar el producto: " + e.getMessage());
        }
    }

    @Override
    public ServiceResponse<ProductoResponse> obtenerProductoPorId(int idProducto) {
        try {
            Optional<ProductoEntity> optionalEntidad = productoRepository.findById(idProducto);

            if (optionalEntidad.isEmpty()) {
                return ServiceResponse.error("No se encontró ningún producto con el ID: " + idProducto);
            }

            ProductoEntity entidad = optionalEntidad.get();
            ProductoResponse response = new ProductoResponse();
            response.setIdProducto(entidad.getIdProducto());
            response.setNombre(entidad.getNombre());
            response.setDescripcion(entidad.getDescripcion());
            response.setStock(entidad.getStock());
            response.setPrecio(entidad.getPrecio());

            return ServiceResponse.exito("Producto encontrado con éxito", response);

        } catch (Exception e) {
            return ServiceResponse.error("Error al buscar el producto por ID: " + e.getMessage());
        }
    }
}
