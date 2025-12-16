package com.dagawon.web.common.mapper;

import com.dagawon.web.common.dto.TbSequenceDto;
import com.dagawon.web.common.entity.TbSequence;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TbSequenceMapper extends DefaultMapper<TbSequenceDto, TbSequence> {

    TbSequenceMapper INSTANCE = Mappers.getMapper(TbSequenceMapper.class);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TbSequence partialUpdate(TbSequenceDto tbWoSequenceDto, @MappingTarget TbSequence tbWoSequence);
}