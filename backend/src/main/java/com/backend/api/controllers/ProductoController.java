package com.backend.api.controllers;

import com.backend.models.dtos.request.ProductoRequest;
import com.backend.models.dtos.response.ProductoResponse;
import com.backend.services.aws.IS3Service;
import com.backend.services.productos.IProductoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/admin/productos")
@RequiredArgsConstructor
public class ProductoController {
    private final IProductoService productoService;
    private final IS3Service s3Service;

    @PostMapping("/crear")
    public ResponseEntity<ProductoResponse> crearProducto(
            @RequestParam("datos") String datosJson,
            @RequestPart("imagen") MultipartFile imagen) {
        try {
            // Convertir JSON a objeto ProductoRequest
            ObjectMapper objectMapper = new ObjectMapper();
            ProductoRequest request = objectMapper.readValue(datosJson, ProductoRequest.class);

            // Subir imagen a S3 y asignar URL
            String imageUrl = s3Service.uploadFile(imagen);
            request.setImagen(imageUrl);

            // Crear producto
            ProductoResponse producto = productoService.crearProducto(request);
            return ResponseEntity.status(201).body(producto);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ProductoResponse> actualizarProducto(
            @PathVariable Long id,
            @RequestParam("datos") String datosJson,
            @RequestPart(value = "imagen", required = false) MultipartFile imagen) {
        try {
            // Convertir JSON a objeto ProductoRequest
            ObjectMapper objectMapper = new ObjectMapper();
            ProductoRequest request = objectMapper.readValue(datosJson, ProductoRequest.class);

            // Si el usuario subio una nueva imagen, la subimos a S3
            if (imagen != null && !imagen.isEmpty()) {
                String imageUrl = s3Service.uploadFile(imagen);
                request.setImagen(imageUrl);
            }

            // Actualizar producto
            ProductoResponse productoActualizado = productoService.actualizarProducto(id, request);
            return ResponseEntity.ok(productoActualizado);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping("/obtener-todos")
    public ResponseEntity<Page<ProductoResponse>> obtenerProductos(Pageable pageable) {
        Page<ProductoResponse> productos = productoService.obtenerProductos(pageable);

        return ResponseEntity.ok(productos);
    }

    @GetMapping("/obtener-por-id/{id}")
    public ResponseEntity<ProductoResponse> obtenerProductoPorId(@PathVariable Long id) {
        ProductoResponse producto = productoService.obtenerProductoPorId(id);

        return ResponseEntity.ok(producto);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar-por-nombre")
    public ResponseEntity<Page<ProductoResponse>> buscarPorNombre(
            @RequestParam String nombre,
            Pageable pageable) {
        Page<ProductoResponse> productos = productoService.buscarPorNombre(nombre, pageable);

        return ResponseEntity.ok(productos);
    }

    @GetMapping("/buscar-por-categoria/{id}")
    public ResponseEntity<Page<ProductoResponse>> buscarPorCategoria(
            @PathVariable Long id,
            Pageable pageable) {
        Page<ProductoResponse> productos = productoService.buscarPorCategoria(id, pageable);

        return ResponseEntity.ok(productos);
    }

    @GetMapping("/buscar-por-marca/{id}")
    public ResponseEntity<Page<ProductoResponse>> buscarPorMarca(
            @PathVariable Long id,
            Pageable pageable) {
        Page<ProductoResponse> productos = productoService.buscarPorMarca(id, pageable);

        return ResponseEntity.ok(productos);
    }

    @GetMapping("/buscar-por-precio")
    public ResponseEntity<Page<ProductoResponse>> buscarPorPrecio(
            @RequestParam Double min,
            @RequestParam Double max,
            Pageable pageable) {
        Page<ProductoResponse> productos = productoService.buscarPorPrecio(min, max, pageable);

        return ResponseEntity.ok(productos);
    }
}
