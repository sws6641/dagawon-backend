package com.dagawon.web.common.mapper;

import com.dagawon.web.common.dto.TbPositionDto;
import com.dagawon.web.common.entity.TbPosition;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {TbCompanyMapper.class})
public interface TbPositionMapper {
    TbPosition toEntity(TbPositionDto tbPositionDto);

    TbPositionDto toDto(TbPosition tbPosition);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TbPosition partialUpdate(TbPositionDto tbPositionDto, @MappingTarget TbPosition tbPosition);

    TbPositionMapper INSTANCE = Mappers.getMapper(TbPositionMapper.class);
}