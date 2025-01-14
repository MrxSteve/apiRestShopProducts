package com.backend.models.dtos.mappers;

import com.backend.models.dtos.request.MarcaRequest;
import com.backend.models.dtos.response.MarcaResponse;
import com.backend.models.entities.MarcaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MarcaMapper {
    MarcaMapper INSTANCE = Mappers.getMapper(MarcaMapper.class);

    MarcaResponse toResponse(MarcaEntity entity);
    MarcaEntity toEntity(MarcaRequest request);
}
