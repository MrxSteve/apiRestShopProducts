package com.backend.services.publics.implement;

import com.backend.api.exceptions.ResourceNotFoundException;
import com.backend.models.dtos.mappers.publics.CategoriaPublicMapper;
import com.backend.models.dtos.response.publics.CategoriaPublicResponse;
import com.backend.models.entities.CategoriaEntity;
import com.backend.models.repositories.CategoriaRepository;
import com.backend.services.publics.interfaces.ICategoriaPublicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoriaPublicServiceImp implements ICategoriaPublicService {
    private final CategoriaRepository categoriaRepository;
    private final CategoriaPublicMapper categoriaPublicMapper;

    @Override
    public Page<CategoriaPublicResponse> obtenerCategorias(Pageable pageable) {
        Page<CategoriaEntity> categorias = categoriaRepository.findAll(pageable);

        return categorias.map(categoriaPublicMapper::toResponse);
    }

    @Override
    public CategoriaPublicResponse obtenerCategoriaPorId(Long id) {
        CategoriaEntity categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria con ID: " + id + " no encontrada"));

        return categoriaPublicMapper.toResponse(categoria);
    }
}
