package com.backend.api.controllers.publics;

import com.backend.models.dtos.response.publics.MarcaPublicResponse;
import com.backend.services.publics.interfaces.IMarcaPublicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/marcas")
@RequiredArgsConstructor
public class MarcaPublicController {
    private final IMarcaPublicService marcaPublicService;

    @GetMapping("/obtener-marcas")
    public ResponseEntity<Page<MarcaPublicResponse>> obtenerMarcas(Pageable pageable) {
        Page<MarcaPublicResponse> marcas = marcaPublicService.obtenerMarcas(pageable);

        return ResponseEntity.ok(marcas);
    }

    @GetMapping("/obtener-marca/{id}")
    public  ResponseEntity<MarcaPublicResponse> obtenerMarcaPorId(@PathVariable Long id) {
        MarcaPublicResponse marca = marcaPublicService.obtenerMarcaPorId(id);

        return ResponseEntity.ok(marca);
    }
}
