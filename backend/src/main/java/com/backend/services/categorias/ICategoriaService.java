package com.backend.services.categorias;

import com.backend.models.dtos.request.CategoriaRequest;
import com.backend.models.dtos.response.CategoriaResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICategoriaService {
    CategoriaResponse crearCategoria(CategoriaRequest request);
    CategoriaResponse actualizarCategoria(Long id, CategoriaRequest request);
    CategoriaResponse obtenerCategoriaPorId(Long id);
    Page<CategoriaResponse> obtenerCategorias(Pageable pageable);
    void eliminarCategoria(Long id);

    // filtros
    Page<CategoriaResponse> buscarCategoriaPorNombre(String nombre, Pageable pageable);
}
