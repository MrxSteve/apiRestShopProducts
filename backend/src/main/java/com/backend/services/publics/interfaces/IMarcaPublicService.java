package com.backend.services.publics.interfaces;

import com.backend.models.dtos.response.publics.MarcaPublicResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMarcaPublicService {
    Page<MarcaPublicResponse> obtenerMarcas(Pageable pageable);
    MarcaPublicResponse obtenerMarcaPorId(Long id);
}
