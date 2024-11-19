package com.congcongjoa.congcongjoa.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.congcongjoa.congcongjoa.dto.OptionDTO;
import com.congcongjoa.congcongjoa.entity.Option;

@Mapper
public interface OptionMapper {

    OptionMapper INSTANCE = Mappers.getMapper(OptionMapper.class);

    @Mapping(target = "menuOptions", ignore = true)
    OptionDTO toOptionDTO(Option option);

    @Mapping(target = "menuOptions", ignore = true)
    Option toOption(OptionDTO optionDTO);

    List<OptionDTO> toOptionDTOList(List<Option> optionList);

    List<Option> toOptionList(List<OptionDTO> optionDTOList);
    
}
