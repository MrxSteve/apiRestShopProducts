package com.backend.api.controllers.publics;

import com.backend.models.dtos.response.publics.CategoriaPublicResponse;
import com.backend.services.publics.interfaces.ICategoriaPublicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/categorias")
@RequiredArgsConstructor
public class CategoriaPublicController {
    private final ICategoriaPublicService categoriaPublicService;

    @GetMapping("/obtener-categorias")
    public ResponseEntity<Page<CategoriaPublicResponse>> obtenerCategorias(Pageable pageable) {
        Page<CategoriaPublicResponse> categorias = categoriaPublicService.obtenerCategorias(pageable);

        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/obtener-categoria/{id}")
    public ResponseEntity<CategoriaPublicResponse> obtenerCategoriaPorId(@PathVariable Long id) {
        CategoriaPublicResponse categoria = categoriaPublicService.obtenerCategoriaPorId(id);

        return ResponseEntity.ok(categoria);
    }
}
