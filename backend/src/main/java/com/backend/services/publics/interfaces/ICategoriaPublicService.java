package com.backend.services.publics.interfaces;

import com.backend.models.dtos.response.publics.CategoriaPublicResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICategoriaPublicService {
    Page<CategoriaPublicResponse> obtenerCategorias(Pageable pageable);
    CategoriaPublicResponse obtenerCategoriaPorId(Long id);
}
