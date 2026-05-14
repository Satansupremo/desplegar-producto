package com.example.producto_oas.controller;

import com.example.producto_oas.dto.request.ProductoRequest;
import com.example.producto_oas.dto.response.ProductoResponse;
import com.example.producto_oas.dto.response.ServiceResponse;
import com.example.producto_oas.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/producto")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    /**
     * LISTAR PRODUCTOS
     */
    @GetMapping("/listar")
    public ResponseEntity<ServiceResponse<List<ProductoResponse>>> listarProductos() {

        ServiceResponse<List<ProductoResponse>> response =
                productoService.listarProductos();

        return ResponseEntity
                .status(response.isOk()
                        ? HttpStatus.OK
                        : HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

    /**
     * REGISTRAR PRODUCTO
     */
    @PostMapping("/registrar")
    public ResponseEntity<ServiceResponse<Integer>> registrarProducto(
            @Valid @RequestBody ProductoRequest productoRequest) {

        ServiceResponse<Integer> response =
                productoService.registrarProducto(productoRequest);

        return ResponseEntity
                .status(response.isOk()
                        ? HttpStatus.CREATED
                        : HttpStatus.BAD_REQUEST)
                .body(response);
    }

    /**
     * BUSCAR PRODUCTO POR ID
     */
    @GetMapping("/buscar/{id}")
    public ResponseEntity<ServiceResponse<ProductoResponse>> obtenerProductoPorId(
            @PathVariable int id) {

        ServiceResponse<ProductoResponse> response =
                productoService.obtenerProductoPorId(id);

        return ResponseEntity
                .status(response.isOk()
                        ? HttpStatus.OK
                        : HttpStatus.NOT_FOUND)
                .body(response);
    }

    /**
     * CARGAR DATOS DE PRUEBA
     */
    @PostMapping("/cargar-pruebas")
    public ResponseEntity<ServiceResponse<String>> cargarDatosDePrueba() {

        List<ProductoRequest> productosPrueba = new ArrayList<>();

        productosPrueba.add(
                new ProductoRequest(
                        "Laptop Gamer Nitro",
                        "Core i7, 16GB RAM, RTX 4060",
                        15,
                        1299.99
                )
        );

        productosPrueba.add(
                new ProductoRequest(
                        "Mouse Ergonómico Inalámbrico",
                        "Mouse óptico recargable 2.4G",
                        50,
                        29.50
                )
        );

        productosPrueba.add(
                new ProductoRequest(
                        "Teclado Mecánico RGB",
                        "Teclado con switches mecánicos azules",
                        30,
                        75.00
                )
        );

        productosPrueba.add(
                new ProductoRequest(
                        "Monitor 27'' Curvo",
                        "Resolución QHD 165Hz para gaming",
                        8,
                        349.90
                )
        );

        int insertadosCorrectamente = 0;

        for (ProductoRequest prod : productosPrueba) {

            ServiceResponse<Integer> res =
                    productoService.registrarProducto(prod);

            if (res.isOk()) {
                insertadosCorrectamente++;
            }
        }

        if (insertadosCorrectamente > 0) {

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(
                            ServiceResponse.exito(
                                    "Se insertaron con éxito "
                                            + insertadosCorrectamente
                                            + " productos de prueba.",
                                    null
                            )
                    );

        } else {

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(
                            ServiceResponse.error(
                                    "No se pudo insertar ningún producto de prueba."
                            )
                    );
        }
    }
}