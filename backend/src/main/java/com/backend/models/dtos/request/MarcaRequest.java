package com.backend.models.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MarcaRequest {
    @NotBlank(message = "El nombre de la marca es obligatorio")
    @Size(min = 1, max = 100, message = "El nombre de la marca debe tener entre 1 y 100 caracteres")
    private String nombre;

    @NotBlank(message = "La descripción de la marca es obligatoria")
    @Size(min = 1, max = 255, message = "La descripción de la marca debe tener entre 1 y 255 caracteres")
    private String descripcion;
}
