package com.dagawon.web.common.mapper;

import com.dagawon.web.common.entity.TbMembDept;
import com.dagawon.web.common.dto.TbMembDeptDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {TbMembMapper.class, TbDeptMapper.class, TbPositionMapper.class})
public interface TbMembDeptMapper extends DefaultMapper<TbMembDeptDto, TbMembDept> {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TbMembDept partialUpdate(TbMembDeptDto tbMembDeptDto, @MappingTarget TbMembDept tbMembDept);

    TbMembDeptMapper INSTANCE = Mappers.getMapper(TbMembDeptMapper.class);
}