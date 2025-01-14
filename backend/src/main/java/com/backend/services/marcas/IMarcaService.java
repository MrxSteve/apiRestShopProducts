package com.backend.services.marcas;

import com.backend.models.dtos.request.MarcaRequest;
import com.backend.models.dtos.response.MarcaResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMarcaService {
    MarcaResponse crearMarca(MarcaRequest request);
    MarcaResponse actualizarMarca(Long id, MarcaRequest request);
    MarcaResponse obtenerMarcaPorId(Long id);
    Page<MarcaResponse> obtenerMarcas(Pageable pageable);
    void eliminarMarca(Long id);

    // filtros
    Page<MarcaResponse> busarMarcaPorNombre(String nombre, Pageable pageable);
}
