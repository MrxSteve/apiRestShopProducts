package com.backend.models.dtos.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CategoriaRequest {
    @NotBlank(message = "El nombre es requerido")
    @Size(min = 1, max = 100, message = "El nombre debe tener entre 1 y 100 caracteres")
    private String nombre;

    @NotBlank(message = "La descripcion es requerida")
    @Size(min = 1, max = 255, message = "La descripcion debe tener entre 1 y 255 caracteres")
    private String descripcion;
}
