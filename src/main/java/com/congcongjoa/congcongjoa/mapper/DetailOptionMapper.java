package com.congcongjoa.congcongjoa.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.congcongjoa.congcongjoa.dto.DetailOptionDTO;
import com.congcongjoa.congcongjoa.entity.DetailOption;

@Mapper
public interface DetailOptionMapper {

    DetailOptionMapper INSTANCE = Mappers.getMapper(DetailOptionMapper.class);

    @Mapping(source = "orderDetail.id", target = "odIdx")
    @Mapping(source = "menuOption.id", target = "moIdx")
    DetailOptionDTO toDetailOptionDTO(DetailOption detailOption);

    @Mapping(source = "odIdx", target = "orderDetail.id")
    @Mapping(source = "moIdx", target = "menuOption.id")
    DetailOption toDetailOption(DetailOptionDTO detailOptionDTO);

    List<DetailOptionDTO> toDetailOptionDTOList(List<DetailOption> detailOptionList);

    List<DetailOption> toDetailOptionList(List<DetailOptionDTO> detailOptionDTOList);
    
}
