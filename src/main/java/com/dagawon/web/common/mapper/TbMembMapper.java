package com.dagawon.web.common.mapper;

import com.dagawon.web.common.dto.TbMembDto;
import com.dagawon.web.common.entity.TbMemb;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {TbCompanyMapper.class})
public interface TbMembMapper {
    TbMemb toEntity(TbMembDto tbMembDto);

    TbMembDto toDto(TbMemb tbMemb);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TbMemb partialUpdate(TbMembDto tbMembDto, @MappingTarget TbMemb tbMemb);

//    TbMembMapper INSTANCE = Mappers.getMapper(TbMembMapper.class);
}