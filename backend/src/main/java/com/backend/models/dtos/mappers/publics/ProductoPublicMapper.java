package com.backend.models.dtos.mappers.publics;

import com.backend.models.dtos.response.publics.ProductoPublicResponse;
import com.backend.models.entities.ProductoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductoPublicMapper {
    ProductoPublicMapper INSTANCE = Mappers.getMapper(ProductoPublicMapper.class);

    @Mapping(source = "categoria.nombre", target = "categoria")
    @Mapping(source = "marca.nombre", target = "marca")
    ProductoPublicResponse toResponse(ProductoEntity entity);
}
