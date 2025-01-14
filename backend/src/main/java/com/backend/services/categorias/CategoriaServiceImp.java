package com.backend.services.categorias;

import com.backend.api.exceptions.ResourceNotFoundException;
import com.backend.models.dtos.mappers.CategoriaMapper;
import com.backend.models.dtos.request.CategoriaRequest;
import com.backend.models.dtos.response.CategoriaResponse;
import com.backend.models.entities.CategoriaEntity;
import com.backend.models.repositories.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImp implements ICategoriaService {
    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    @Override
    public CategoriaResponse crearCategoria(CategoriaRequest request) {
        CategoriaEntity categoria = categoriaMapper.toEntity(request);
        CategoriaEntity categoriaGuardada = categoriaRepository.save(categoria);

        return categoriaMapper.toResponse(categoriaGuardada);
    }

    @Override
    public CategoriaResponse actualizarCategoria(Long id, CategoriaRequest request) {
        CategoriaEntity categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria con ID: " + id + " no encontrada"));

        categoria.setNombre(request.getNombre());
        categoria.setDescripcion(request.getDescripcion());

        categoriaRepository.save(categoria);
        return categoriaMapper.toResponse(categoria);
    }

    @Override
    public CategoriaResponse obtenerCategoriaPorId(Long id) {
        CategoriaEntity categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria con ID: " + id + " no encontrada"));

        return categoriaMapper.toResponse(categoria);
    }

    @Override
    public Page<CategoriaResponse> obtenerCategorias(Pageable pageable) {
        Page<CategoriaEntity> categorias = categoriaRepository.findAll(pageable);

        return categorias.map(categoriaMapper::toResponse);
    }

    @Override
    public void eliminarCategoria(Long id) {
        CategoriaEntity categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria con ID: " + id + " no encontrada"));

        categoriaRepository.delete(categoria);
    }

    @Override
    public Page<CategoriaResponse> buscarCategoriaPorNombre(String nombre, Pageable pageable) {
        Page<CategoriaEntity> categorias = categoriaRepository.findByNombreContainingIgnoreCase(nombre, pageable);

        return categorias.map(categoriaMapper::toResponse);
    }

}
