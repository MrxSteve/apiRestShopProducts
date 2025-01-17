package com.backend.models.dtos.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class ProductoRequest {
    @NotBlank(message = "El nombre es requerido")
    @Size(min = 1, max = 100, message = "El nombre debe tener entre 1 y 100 caracteres")
    private String nombre;

    @Size(max = 255, message = "La descripcion debe tener maximo 255 caracteres")
    private String descripcion;

    @NotNull(message = "El precio de compra es requerido")
    @Positive(message = "El precio de compra debe ser mayor a 0")
    private Double precioCompra;

    @NotNull(message = "El precio de venta es requerido")
    @Positive(message = "El precio de venta debe ser mayor a 0")
    private Double precioVenta;

    @Size(max = 512, message = "La imagen debe tener como maximo 512 caracteres")
    private String imagen; // Aca no mandamos nada, se asignara la url de aws en el controlador

    @NotNull(message = "El stock es requerido")
    @Min(value = 0, message = "El stock debe ser mayor o igual a 0")
    private Integer stock;

    @NotNull(message = "La categoría es requerida")
    private Long categoriaId;

    @NotNull(message = "La marca es requerida")
    private Long marcaId;

//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private LocalDate fechaEntrada;
}
