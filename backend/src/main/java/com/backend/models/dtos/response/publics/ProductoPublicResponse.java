package com.backend.models.dtos.response.publics;

import lombok.Data;

@Data
public class ProductoPublicResponse {
    private String nombre;
    private String descripcion;
    private Double precioVenta;
    private String imagen;
    private Integer stock;
    private String categoria;
    private String marca;
}
