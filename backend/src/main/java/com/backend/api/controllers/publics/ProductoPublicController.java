package com.backend.api.controllers.publics;

import com.backend.models.dtos.response.publics.ProductoPublicResponse;
import com.backend.services.publics.interfaces.IProductoPublicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/productos")
@RequiredArgsConstructor
public class ProductoPublicController {
    private final IProductoPublicService productoPublicService;

    @GetMapping("/obtener-todos")
    public ResponseEntity<Page<ProductoPublicResponse>> obtenerProductos(Pageable pageable) {
        Page<ProductoPublicResponse> productos = productoPublicService.obtenerProductos(pageable);

        return ResponseEntity.ok(productos);
    }

    @GetMapping("/buscar-por-nombre")
    public ResponseEntity<Page<ProductoPublicResponse>> buscarPorNombre(
            @RequestParam String nombre,
            Pageable pageable) {
        Page<ProductoPublicResponse> productos = productoPublicService.buscarPorNombre(nombre, pageable);

        return ResponseEntity.ok(productos);
    }

    @GetMapping("/buscar-por-categoria/{id}")
    public ResponseEntity<Page<ProductoPublicResponse>> buscarPorCategoria(
            @PathVariable Long id,
            Pageable pageable) {
        Page<ProductoPublicResponse> productos = productoPublicService.buscarPorCategoria(id, pageable);

        return ResponseEntity.ok(productos);
    }

    @GetMapping("/buscar-por-marca/{id}")
    public ResponseEntity<Page<ProductoPublicResponse>> buscarPorMarca(
            @PathVariable Long id,
            Pageable pageable) {
        Page<ProductoPublicResponse> productos = productoPublicService.buscarPorMarca(id, pageable);

        return ResponseEntity.ok(productos);
    }

    @GetMapping("/buscar-por-precio")
    public ResponseEntity<Page<ProductoPublicResponse>> buscarPorPrecio(
            @RequestParam Double min,
            @RequestParam Double max,
            Pageable pageable) {
        Page<ProductoPublicResponse> productos = productoPublicService.buscarPorPrecio(min, max, pageable);

        return ResponseEntity.ok(productos);
    }

    @GetMapping("/obtener-por-id/{id}")
    public ResponseEntity<ProductoPublicResponse> obtenerProductoPorId(@PathVariable Long id) {
        ProductoPublicResponse producto = productoPublicService.obtenerProductoPorId(id);

        return ResponseEntity.ok(producto);
    }
}
