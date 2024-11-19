package com.congcongjoa.congcongjoa.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.congcongjoa.congcongjoa.dto.MenuOptionDTO;
import com.congcongjoa.congcongjoa.entity.MenuOption;

@Mapper
public interface MenuOptionMapper {

    MenuOptionMapper INSTANCE = Mappers.getMapper(MenuOptionMapper.class);

    @Mapping(source = "menu.id", target = "mnIdx")
    @Mapping(source = "option.id", target = "opIdx")
    @Mapping(target = "detailOptions", ignore = true)
    MenuOptionDTO toMenuOptionDTO(MenuOption menuOption);

    @Mapping(source = "mnIdx", target = "menu.id")
    @Mapping(source = "opIdx", target = "option.id")
    @Mapping(target = "detailOptions", ignore = true)
    MenuOption toMenuOption(MenuOptionDTO menuOptionDTO);

    List<MenuOptionDTO> toMenuOptionDTOList(List<MenuOption> menuOptionList);

    List<MenuOption> toMenuOptionList(List<MenuOptionDTO> menuOptionDTOList);
    
}
