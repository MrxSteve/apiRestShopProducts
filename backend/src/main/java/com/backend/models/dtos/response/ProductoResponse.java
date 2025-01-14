package com.backend.models.dtos.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProductoResponse {
    private Long id;
    private String nombre;
    private String descripcion;
    private Double precioCompra;
    private Double precioVenta;
    private String imagen;
    private Integer stock;
    private String categoria;
    private String marca;
    private LocalDate fechaEntrada;
}
