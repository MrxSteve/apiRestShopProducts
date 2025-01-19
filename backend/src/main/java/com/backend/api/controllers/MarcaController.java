package com.backend.api.controllers;

import com.backend.models.dtos.request.MarcaRequest;
import com.backend.models.dtos.response.MarcaResponse;
import com.backend.services.marcas.IMarcaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/marcas")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class MarcaController {
    private final IMarcaService marcaService;

    @PostMapping("/crear")
    public ResponseEntity<MarcaResponse> crearMarca(@Valid @RequestBody MarcaRequest request) {
        MarcaResponse marca = marcaService.crearMarca(request);

        return ResponseEntity.status(201).body(marca);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<MarcaResponse> actualizarMarca(
            @PathVariable Long id,
            @Valid @RequestBody MarcaRequest request) {
        MarcaResponse marca = marcaService.actualizarMarca(id, request);

        return ResponseEntity.ok(marca);
    }

    @GetMapping("/obtener-todas")
    public ResponseEntity<Page<MarcaResponse>> obtenerMarcas(Pageable pageable) {
        Page<MarcaResponse> marcas = marcaService.obtenerMarcas(pageable);

        return ResponseEntity.ok(marcas);
    }

    @GetMapping("/obtener-por-id/{id}")
    public ResponseEntity<MarcaResponse> obtenerMarcaPorId(@PathVariable Long id) {
        MarcaResponse marca = marcaService.obtenerMarcaPorId(id);

        return ResponseEntity.ok(marca);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarMarca(@PathVariable Long id) {
        marcaService.eliminarMarca(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar-por-nombre")
    public ResponseEntity<Page<MarcaResponse>> buscarPorNombre(
            @RequestParam String nombre,
            Pageable pageable) {
        Page<MarcaResponse> marcas = marcaService.busarMarcaPorNombre(nombre, pageable);

        return ResponseEntity.ok(marcas);
    }
}
