package com.backend.models.repositories;

import com.backend.models.entities.ProductoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> {
    // Buscar por nombre
    Page<ProductoEntity> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
    // Buscar por precio
    Page<ProductoEntity> findByPrecioVentaBetween(Double min, Double max, Pageable pageable);
    // Buscar por categoria
    Page<ProductoEntity> findByCategoria_Id(Long id, Pageable pageable);
    // Buscar por marca
    Page<ProductoEntity> findByMarca_Id(Long id, Pageable pageable);
}
