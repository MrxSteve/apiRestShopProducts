package com.backend.models.dtos.mappers.publics;

import com.backend.models.dtos.response.publics.MarcaPublicResponse;
import com.backend.models.entities.MarcaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = ProductoPublicMapper.class)
public interface MarcaPublicMapper {
    MarcaPublicMapper INSTANCE = Mappers.getMapper(MarcaPublicMapper.class);

    @Mapping(source = "productos", target = "productos")
    MarcaPublicResponse toResponse(MarcaEntity entity);
}
