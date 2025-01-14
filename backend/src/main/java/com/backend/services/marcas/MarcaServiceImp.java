package com.backend.services.marcas;

import com.backend.api.exceptions.ResourceNotFoundException;
import com.backend.models.dtos.mappers.MarcaMapper;
import com.backend.models.dtos.request.MarcaRequest;
import com.backend.models.dtos.response.MarcaResponse;
import com.backend.models.entities.MarcaEntity;
import com.backend.models.repositories.MarcaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MarcaServiceImp implements IMarcaService {
    private final MarcaRepository marcaRepository;
    private final MarcaMapper marcaMapper;

    @Override
    public MarcaResponse crearMarca(MarcaRequest request) {
        MarcaEntity marca = marcaMapper.toEntity(request);
        MarcaEntity marcaGuardada = marcaRepository.save(marca);

        return marcaMapper.toResponse(marcaGuardada);
    }

    @Override
    public MarcaResponse actualizarMarca(Long id, MarcaRequest request) {
        MarcaEntity marca = marcaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Marca con ID: " + id + " no encontrada"));

        marca.setNombre(request.getNombre());
        marca.setDescripcion(request.getDescripcion());

        marcaRepository.save(marca);
        return marcaMapper.toResponse(marca);
    }

    @Override
    public MarcaResponse obtenerMarcaPorId(Long id) {
        MarcaEntity marca = marcaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Marca con ID: " + id + " no encontrada"));

        return marcaMapper.toResponse(marca);
    }

    @Override
    public Page<MarcaResponse> obtenerMarcas(Pageable pageable) {
        Page<MarcaEntity> marcas = marcaRepository.findAll(pageable);

        return marcas.map(marcaMapper::toResponse);
    }

    @Override
    public void eliminarMarca(Long id) {
        MarcaEntity marca = marcaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Marca con ID: " + id + " no encontrada"));

        marcaRepository.delete(marca);
    }

    @Override
    public Page<MarcaResponse> busarMarcaPorNombre(String nombre, Pageable pageable) {
        Page<MarcaEntity> marcas = marcaRepository.findByNombreContainingIgnoreCase(nombre, pageable);

        return marcas.map(marcaMapper::toResponse);
    }
}
