package com.congcongjoa.congcongjoa.mapper;

import com.congcongjoa.congcongjoa.dto.MenuDTO;
import com.congcongjoa.congcongjoa.entity.Menu;
import com.congcongjoa.congcongjoa.mapper.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {AllergyMapper.class, NutritionMapper.class, MenuOptionMapper.class, StoreMenuMapper.class, ImageMapper.class})
public interface MenuMapper {

    MenuMapper INSTANCE = Mappers.getMapper(MenuMapper.class);

    // Menu -> MenuDTO
    @Mapping(target = "allergy", source = "allergy")
    @Mapping(target = "nutrition", source = "nutrition")
    @Mapping(target = "menuOption", source = "menuOption")
    @Mapping(target = "storeMenu", source = "storeMenu")
    @Mapping(target = "images", source = "images")
    MenuDTO toMenuDTO(Menu menu);

    // MenuDTO -> Menu
    @Mapping(target = "allergy", source = "allergy")
    @Mapping(target = "nutrition", source = "nutrition")
    @Mapping(target = "menuOption", source = "menuOption")
    @Mapping(target = "storeMenu", source = "storeMenu")
    @Mapping(target = "images", source = "images")
    Menu toMenu(MenuDTO menuDTO);

    // 컬렉션 매핑
    List<MenuDTO> toMenuDTOList(List<Menu> menuList);

    List<Menu> toMenuList(List<MenuDTO> menuDTOList);
}