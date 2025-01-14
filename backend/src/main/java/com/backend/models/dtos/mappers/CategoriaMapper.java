package com.backend.models.dtos.mappers;

import com.backend.models.dtos.request.CategoriaRequest;
import com.backend.models.dtos.response.CategoriaResponse;
import com.backend.models.entities.CategoriaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
    CategoriaMapper INSTANCE = Mappers.getMapper(CategoriaMapper.class);

    CategoriaResponse toResponse(CategoriaEntity entity);
    CategoriaEntity toEntity(CategoriaRequest request);
}
