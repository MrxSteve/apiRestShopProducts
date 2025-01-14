package com.backend.api.controllers;

import com.backend.models.dtos.request.ProductoRequest;
import com.backend.models.dtos.response.ProductoResponse;
import com.backend.services.productos.IProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/productos")
@RequiredArgsConstructor
public class ProductoController {
    private final IProductoService productoService;

    @PostMapping("/crear")
    public ResponseEntity<ProductoResponse> crearProducto(@Valid @RequestBody ProductoRequest request) {
        ProductoResponse producto = productoService.crearProducto(request);

        return  ResponseEntity.status(201).body(producto);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ProductoResponse> actualizarProducto(
            @PathVariable Long id,
            @Valid @RequestBody ProductoRequest request) {
        ProductoResponse producto = productoService.actualizarProducto(id, request);

        return ResponseEntity.ok(producto);
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
