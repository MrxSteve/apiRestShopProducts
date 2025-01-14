package com.backend.services.publics.implement;

import com.backend.api.exceptions.ResourceNotFoundException;
import com.backend.models.dtos.mappers.publics.MarcaPublicMapper;
import com.backend.models.dtos.response.publics.MarcaPublicResponse;
import com.backend.models.entities.MarcaEntity;
import com.backend.models.repositories.MarcaRepository;
import com.backend.services.publics.interfaces.IMarcaPublicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MarcaPublicServiceImp implements IMarcaPublicService {
    private final MarcaRepository marcaRepository;
    private final MarcaPublicMapper marcaPublicMapper;

    @Override
    public Page<MarcaPublicResponse> obtenerMarcas(Pageable pageable) {
        Page<MarcaEntity> marcas = marcaRepository.findAll(pageable);

        return marcas.map(marcaPublicMapper::toResponse);
    }

    @Override
    public MarcaPublicResponse obtenerMarcaPorId(Long id) {
        MarcaEntity marca = marcaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Marca con ID: " + id + " no encontrada"));

        return marcaPublicMapper.toResponse(marca);
    }
}
