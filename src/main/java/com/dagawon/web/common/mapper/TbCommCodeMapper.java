package com.dagawon.web.common.mapper;

import com.dagawon.web.common.dto.TbCommCodeDto;
import com.dagawon.web.common.entity.TbCommCode;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TbCommCodeMapper extends DefaultMapper<TbCommCodeDto, TbCommCode> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TbCommCode partialUpdate(TbCommCodeDto tbCommCodeDto, @MappingTarget TbCommCode tbCommCode);
}