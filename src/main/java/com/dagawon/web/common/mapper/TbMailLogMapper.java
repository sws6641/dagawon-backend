package com.dagawon.web.common.mapper;

import com.dagawon.web.common.dto.TbMailLogDto;
import com.dagawon.web.common.entity.TbMailLog;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TbMailLogMapper extends DefaultMapper<TbMailLogDto, TbMailLog> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TbMailLog partialUpdate(TbMailLogDto tbMailLogDto, @MappingTarget TbMailLog tbMailLog);
}