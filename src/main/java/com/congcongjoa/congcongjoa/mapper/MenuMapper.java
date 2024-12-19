package com.congcongjoa.congcongjoa.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.congcongjoa.congcongjoa.dto.AllergyDTO;
import com.congcongjoa.congcongjoa.dto.MenuDTO;
import com.congcongjoa.congcongjoa.dto.NutritionDTO;
import com.congcongjoa.congcongjoa.entity.Allergy;
import com.congcongjoa.congcongjoa.entity.Menu;
import com.congcongjoa.congcongjoa.entity.Nutrition;

@Mapper
public interface MenuMapper {

    MenuMapper INSTANCE = Mappers.getMapper(MenuMapper.class);

    @Mapping(target = "allergy", source = "allergy")
    @Mapping(target = "nutrition", source = "nutrition")
    @Mapping(target = "menuOption", source = "menuOption")
    @Mapping(target = "storeMenu", source = "storeMenu")
    @Mapping(target = "images", source = "images")
    MenuDTO toMenuDTO(Menu menu);

    @Mapping(target = "allergy", source = "allergy")
    @Mapping(target = "nutrition", source = "nutrition")
    @Mapping(target = "menuOption", source = "menuOption")
    @Mapping(target = "storeMenu", source = "storeMenu")
    @Mapping(target = "images", source = "images")
    Menu toMenu(MenuDTO menuDTO);

    List<MenuDTO> toMenuDTOList(List<Menu> menuList);

    List<Menu> toMenuList(List<MenuDTO> menuDTOList);

}
