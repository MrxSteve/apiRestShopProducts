package com.backend.models.repositories;

import com.backend.models.entities.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<RolEntity, Long> {
    RolEntity findByNombre(String nombre);
}
