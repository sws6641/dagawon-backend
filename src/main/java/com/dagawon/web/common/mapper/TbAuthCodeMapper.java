package com.dagawon.web.common.mapper;

import com.dagawon.web.common.dto.TbAuthCodeDto;
import com.dagawon.web.common.entity.TbAuthCode;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TbAuthCodeMapper extends DefaultMapper<TbAuthCodeDto, TbAuthCode> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TbAuthCode partialUpdate(TbAuthCodeDto tbAuthCodeDto, @MappingTarget TbAuthCode tbAuthCode);
}