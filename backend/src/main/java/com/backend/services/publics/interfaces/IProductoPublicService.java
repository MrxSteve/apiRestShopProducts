package com.backend.services.publics.interfaces;

import com.backend.models.dtos.response.publics.ProductoPublicResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductoPublicService {
    Page<ProductoPublicResponse> obtenerProductos(Pageable pageable);
    Page<ProductoPublicResponse> buscarPorNombre(String nombre, Pageable pageable);
    Page<ProductoPublicResponse> buscarPorCategoria(Long idCategoria, Pageable pageable);
    Page<ProductoPublicResponse> buscarPorMarca(Long idMarca, Pageable pageable);
    Page<ProductoPublicResponse> buscarPorPrecio(Double min, Double max, Pageable pageable);
    ProductoPublicResponse obtenerProductoPorId(Long id);
}
