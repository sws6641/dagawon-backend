package com.dagawon.web.common.mapper;

import com.dagawon.web.common.dto.TbTermsDto;
import com.dagawon.web.common.entity.TbTerms;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {TbCompanyMapper.class})
public interface TbTermsMapper extends DefaultMapper<TbTermsDto, TbTerms> {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TbTerms partialUpdate(TbTermsDto tbTermsDto, @MappingTarget TbTerms tbTerms);

}