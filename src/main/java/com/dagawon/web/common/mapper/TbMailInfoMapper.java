package com.dagawon.web.common.mapper;

import com.dagawon.web.common.dto.TbMailInfoDto;
import com.dagawon.web.common.entity.TbMailInfo;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TbMailInfoMapper extends DefaultMapper<TbMailInfoDto, TbMailInfo> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TbMailInfo partialUpdate(TbMailInfoDto tbMailInfoDto, @MappingTarget TbMailInfo tbMailInfo);
}