package com.backend.models.repositories;

import com.backend.models.entities.MarcaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaRepository extends JpaRepository<MarcaEntity, Long> {
    // Buscar por nombre
    Page<MarcaEntity> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
}
