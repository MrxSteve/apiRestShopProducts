package com.backend.services.publics.implement;

import com.backend.api.exceptions.ResourceNotFoundException;
import com.backend.models.dtos.mappers.publics.ProductoPublicMapper;
import com.backend.models.dtos.response.publics.ProductoPublicResponse;
import com.backend.models.entities.ProductoEntity;
import com.backend.models.repositories.ProductoRepository;
import com.backend.services.publics.interfaces.IProductoPublicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductoPublicServiceImp implements IProductoPublicService {
    private final ProductoRepository productoRepository;
    private final ProductoPublicMapper productoPublicMapper;

    @Override
    public Page<ProductoPublicResponse> obtenerProductos(Pageable pageable) {
        Page<ProductoEntity> productos = productoRepository.findAll(pageable);

        return productos.map(productoPublicMapper::toResponse);
    }

    @Override
    public Page<ProductoPublicResponse> buscarPorNombre(String nombre, Pageable pageable) {
        Page<ProductoEntity> productos = productoRepository.findByNombreContainingIgnoreCase(nombre, pageable);

        return productos.map(productoPublicMapper::toResponse);
    }

    @Override
    public Page<ProductoPublicResponse> buscarPorCategoria(Long idCategoria, Pageable pageable) {
        Page<ProductoEntity> productos = productoRepository.findByCategoria_Id(idCategoria, pageable);

        return productos.map(productoPublicMapper::toResponse);
    }

    @Override
    public Page<ProductoPublicResponse> buscarPorMarca(Long idMarca, Pageable pageable) {
        Page<ProductoEntity> productos = productoRepository.findByMarca_Id(idMarca, pageable);

        return productos.map(productoPublicMapper::toResponse);
    }

    @Override
    public Page<ProductoPublicResponse> buscarPorPrecio(Double min, Double max, Pageable pageable) {
        Page<ProductoEntity> productos = productoRepository.findByPrecioVentaBetween(min, max, pageable);

        return productos.map(productoPublicMapper::toResponse);
    }

    @Override
    public ProductoPublicResponse obtenerProductoPorId(Long id) {
        ProductoEntity producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto con ID: " + id + " no encontrado"));

        return productoPublicMapper.toResponse(producto);
    }
}
