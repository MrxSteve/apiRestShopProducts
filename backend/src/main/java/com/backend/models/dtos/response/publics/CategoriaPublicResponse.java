package com.backend.models.dtos.response.publics;

import lombok.Data;

import java.util.List;

@Data
public class CategoriaPublicResponse {
    private String nombre;
    private List<ProductoPublicResponse> productos;
}
