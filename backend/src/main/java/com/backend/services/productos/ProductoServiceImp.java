package com.backend.services.productos;

import com.backend.api.exceptions.ResourceNotFoundException;
import com.backend.models.dtos.mappers.ProductoMapper;
import com.backend.models.dtos.request.ProductoRequest;
import com.backend.models.dtos.response.ProductoResponse;
import com.backend.models.entities.ProductoEntity;
import com.backend.models.repositories.CategoriaRepository;
import com.backend.models.repositories.MarcaRepository;
import com.backend.models.repositories.ProductoRepository;
import com.backend.services.aws.IS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductoServiceImp implements IProductoService{
    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final MarcaRepository marcaRepository;
    private final ProductoMapper productoMapper;
    private final IS3Service s3Service;

    @Override
    public ProductoResponse crearProducto(ProductoRequest request) {
        ProductoEntity producto = productoMapper.toEntity(request);

        // Buscar y asignar las relaciones
        producto.setCategoria(categoriaRepository.findById(request.getCategoriaId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria con ID: " + request.getCategoriaId() + " no encontrada")));

        producto.setMarca(marcaRepository.findById(request.getMarcaId())
                .orElseThrow(() -> new ResourceNotFoundException("Marca con ID: " + request.getMarcaId() + " no encontrada")));

        ProductoEntity productoGuardado = productoRepository.save(producto);
        return productoMapper.toResponse(productoGuardado);
    }

    @Override
    public ProductoResponse actualizarProducto(Long id, ProductoRequest request) {
        ProductoEntity producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto con ID: " + id + " no encontrado"));

        // Si se envia una nueva imagen, eliminar la anterior de S3 y guardar la nueva
        if (request.getImagen() != null && !request.getImagen().isEmpty() &&
                !request.getImagen().equals(producto.getImagen())) {
            s3Service.deleteFile(producto.getImagen());
            producto.setImagen(request.getImagen());
        }

        // Solo actualizar si los valores no son null o vacíos
        if (request.getNombre() != null && !request.getNombre().isEmpty()) {
            producto.setNombre(request.getNombre());
        }
        if (request.getDescripcion() != null && !request.getDescripcion().isEmpty()) {
            producto.setDescripcion(request.getDescripcion());
        }
        if (request.getPrecioCompra() != null) {
            producto.setPrecioCompra(request.getPrecioCompra());
        }
        if (request.getPrecioVenta() != null) {
            producto.setPrecioVenta(request.getPrecioVenta());
        }
        if (request.getStock() != null) {
            producto.setStock(request.getStock());
        }

        // Validar y asignar la categoria si se proporciona
        if (request.getCategoriaId() != null) {
            producto.setCategoria(categoriaRepository.findById(request.getCategoriaId())
                    .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada")));
        }

        // Validar y asignar la marca si se proporciona
        if (request.getMarcaId() != null) {
            producto.setMarca(marcaRepository.findById(request.getMarcaId())
                    .orElseThrow(() -> new ResourceNotFoundException("Marca no encontrada")));
        }

        productoRepository.save(producto);
        return productoMapper.toResponse(producto);
    }

    @Override
    public Page<ProductoResponse> obtenerProductos(Pageable pageable) {
        Page<ProductoEntity> productos = productoRepository.findAll(pageable);
        // Convertir la lista de ProductoEnitity a una lista de ProductoResponse
        return productos.map(productoMapper::toResponse);
    }

    @Override
    public ProductoResponse obtenerProductoPorId(Long id) {
        ProductoEntity producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto con ID: " + id + " no encontrado"));
        return productoMapper.toResponse(producto);
    }

    @Override
    public void eliminarProducto(Long id) {
        ProductoEntity producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto con ID: " + id + " no encontrado"));

        // Eliminar imagen de S3 antes de eliminar el producto
        if (producto.getImagen() != null && !producto.getImagen().isEmpty()) {
            s3Service.deleteFile(producto.getImagen());
        }

        productoRepository.delete(producto);
    }

    @Override
    public Page<ProductoResponse> buscarPorNombre(String nombre, Pageable pageable) {
        Page<ProductoEntity> productos = productoRepository.findByNombreContainingIgnoreCase(nombre, pageable);

        return productos.map(productoMapper::toResponse);
    }

    @Override
    public Page<ProductoResponse> buscarPorCategoria(Long idCategoria, Pageable pageable) {
        Page<ProductoEntity> productos = productoRepository.findByCategoria_Id(idCategoria, pageable);

        return productos.map(productoMapper::toResponse);
    }

    @Override
    public Page<ProductoResponse> buscarPorMarca(Long idMarca, Pageable pageable) {
        Page<ProductoEntity> productos = productoRepository.findByMarca_Id(idMarca, pageable);

        return productos.map(productoMapper::toResponse);
    }

    @Override
    public Page<ProductoResponse> buscarPorPrecio(Double min, Double max, Pageable pageable) {
        Page<ProductoEntity> productos = productoRepository.findByPrecioVentaBetween(min, max, pageable);

        return productos.map(productoMapper::toResponse);
    }
}
