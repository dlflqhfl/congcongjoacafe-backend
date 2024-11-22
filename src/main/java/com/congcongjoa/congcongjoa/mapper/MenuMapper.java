package com.congcongjoa.congcongjoa.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.congcongjoa.congcongjoa.dto.MenuDTO;
import com.congcongjoa.congcongjoa.entity.Menu;

@Mapper
public interface MenuMapper {

    MenuMapper INSTANCE = Mappers.getMapper(MenuMapper.class);

    @Mapping(target = "allergy", ignore = true)
    @Mapping(target = "nutrition", ignore = true)
    @Mapping(target = "menuOption", ignore = true)
    @Mapping(target = "storeMenu", ignore = true)
    @Mapping(target = "images", ignore = true)
    MenuDTO toMenuDTO(Menu menu);

    @Mapping(target = "allergy", ignore = true)
    @Mapping(target = "nutrition", ignore = true)
    @Mapping(target = "menuOption", ignore = true)
    @Mapping(target = "storeMenu", ignore = true)
    @Mapping(target = "images", ignore = true)
    Menu toMenu(MenuDTO menuDTO);

    List<MenuDTO> toMenuDTOList(List<Menu> menuList);

    List<Menu> toMenuList(List<MenuDTO> menuDTOList);
    
}
