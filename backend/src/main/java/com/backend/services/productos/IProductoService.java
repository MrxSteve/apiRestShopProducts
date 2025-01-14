package com.backend.services.productos;

import com.backend.models.dtos.request.ProductoRequest;
import com.backend.models.dtos.response.ProductoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductoService {
    ProductoResponse crearProducto(ProductoRequest request);
    ProductoResponse actualizarProducto(Long id, ProductoRequest request);
    Page<ProductoResponse> obtenerProductos(Pageable pageable);
    ProductoResponse obtenerProductoPorId(Long id);
    void eliminarProducto(Long id);

    // Filtros
    Page<ProductoResponse> buscarPorNombre(String nombre, Pageable pageable);
    Page<ProductoResponse> buscarPorCategoria(Long idCategoria, Pageable pageable);
    Page<ProductoResponse> buscarPorMarca(Long idMarca, Pageable pageable);
    Page<ProductoResponse> buscarPorPrecio(Double min, Double max, Pageable pageable);
}
