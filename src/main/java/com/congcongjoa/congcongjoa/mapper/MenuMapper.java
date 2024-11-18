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

    @Mapping(target = "allergies", ignore = true)
    @Mapping(target = "nutritions", ignore = true)
    @Mapping(target = "menuOptions", ignore = true)
    @Mapping(target = "storeMenus", ignore = true)
    @Mapping(target = "images", ignore = true)
    MenuDTO toMenuDTO(Menu menu);

    @Mapping(target = "allergies", ignore = true)
    @Mapping(target = "nutritions", ignore = true)
    @Mapping(target = "menuOptions", ignore = true)
    @Mapping(target = "storeMenus", ignore = true)
    @Mapping(target = "images", ignore = true)
    Menu toMenu(MenuDTO menuDTO);

    List<MenuDTO> toMenuDTOList(List<Menu> menuList);

    List<Menu> toMenuList(List<MenuDTO> menuDTOList);
    
}
