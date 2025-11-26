package com.dagawon.web.common.mapper;

import com.dagawon.web.common.dto.TbDeptDto;
import com.dagawon.web.common.entity.TbDept;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {TbCompanyMapper.class})
public interface TbDeptMapper extends DefaultMapper<TbDeptDto, TbDept> {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TbDept partialUpdate(TbDeptDto tbDeptDto, @MappingTarget TbDept tbDept);

    TbDeptMapper INSTANCE = Mappers.getMapper(TbDeptMapper.class);
}