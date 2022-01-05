package com.company.mapper;

import com.company.dao.entity.Farm;
import com.company.model.dto.FarmDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FarmMapper {

    FarmMapper INSTANCE= Mappers.getMapper(FarmMapper.class);

    @Mappings({ @Mapping(target = "id",source = "id")})
    Farm toEntity(FarmDto farmDto);

    @Mappings({ @Mapping(target = "id",source = "id")})
    FarmDto toDto(Farm farm);

}
