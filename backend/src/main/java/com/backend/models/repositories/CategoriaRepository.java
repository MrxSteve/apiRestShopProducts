package com.backend.models.repositories;

import com.backend.models.entities.CategoriaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> {
    // Buscar por nombre
    Page<CategoriaEntity> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
}
