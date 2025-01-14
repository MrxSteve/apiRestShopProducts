package com.backend.api.controllers;

import com.backend.models.dtos.request.CategoriaRequest;
import com.backend.models.dtos.response.CategoriaResponse;
import com.backend.services.categorias.ICategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/categorias")
@RequiredArgsConstructor
public class CategoriaController {
    private final ICategoriaService categoriaService;

    @PostMapping("/crear")
    public ResponseEntity<CategoriaResponse> crearCategoria(@Valid @RequestBody CategoriaRequest request) {
        CategoriaResponse categoria = categoriaService.crearCategoria(request);

        return ResponseEntity.status(201).body(categoria);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<CategoriaResponse> actualizarCategoria(
            @PathVariable Long id,
            @Valid @RequestBody CategoriaRequest request) {
        CategoriaResponse categoria = categoriaService.actualizarCategoria(id, request);

        return ResponseEntity.ok(categoria);
    }

    @GetMapping("/obtener-todas")
    public ResponseEntity<Page<CategoriaResponse>> obtenerCategorias(Pageable pageable) {
        Page<CategoriaResponse> categorias = categoriaService.obtenerCategorias(pageable);

        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/obtener-por-id/{id}")
    public ResponseEntity<CategoriaResponse> obtenerCategoriaPorId(@PathVariable Long id) {
        CategoriaResponse categoria = categoriaService.obtenerCategoriaPorId(id);

        return ResponseEntity.ok(categoria);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id) {
        categoriaService.eliminarCategoria(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar-por-nombre")
    public ResponseEntity<Page<CategoriaResponse>> buscarPorNombre(
            @RequestParam String nombre,
            Pageable pageable) {
        Page<CategoriaResponse> categorias = categoriaService.buscarCategoriaPorNombre(nombre, pageable);

        return ResponseEntity.ok(categorias);
    }
}
