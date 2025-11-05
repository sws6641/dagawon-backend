package com.dagawon.web.common.mapper;

import com.dagawon.web.common.dto.TbCompanyDto;
import com.dagawon.web.common.entity.TbCompany;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TbCompanyMapper {
    TbCompany toEntity(TbCompanyDto tbCompanyDto);

    TbCompanyDto toDto(TbCompany tbCompany);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TbCompany partialUpdate(TbCompanyDto tbCompanyDto, @MappingTarget TbCompany tbCompany);

    TbCompanyMapper INSTANCE = Mappers.getMapper(TbCompanyMapper.class);
}