package com.backend.models.dtos.mappers;

import com.backend.models.dtos.request.ProductoRequest;
import com.backend.models.dtos.response.ProductoResponse;
import com.backend.models.entities.ProductoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductoMapper {
    ProductoMapper INSTANCE = Mappers.getMapper(ProductoMapper.class);

    @Mapping(source = "categoria.nombre", target = "categoria")
    @Mapping(source = "marca.nombre", target = "marca")
    ProductoResponse toResponse(ProductoEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "categoriaId", target = "categoria.id")
    @Mapping(source = "marcaId", target = "marca.id")
    ProductoEntity toEntity(ProductoRequest request);
}
