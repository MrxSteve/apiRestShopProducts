package com.backend.models.dtos.mappers.publics;

import com.backend.models.dtos.response.publics.CategoriaPublicResponse;
import com.backend.models.entities.CategoriaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = ProductoPublicMapper.class)
public interface CategoriaPublicMapper {
    CategoriaPublicMapper INSTANCE = Mappers.getMapper(CategoriaPublicMapper.class);

    @Mapping(source = "productos", target = "productos")
    CategoriaPublicResponse toResponse(CategoriaEntity entity);
}
