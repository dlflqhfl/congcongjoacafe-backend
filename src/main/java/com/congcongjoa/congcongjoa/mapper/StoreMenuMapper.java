package com.congcongjoa.congcongjoa.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.congcongjoa.congcongjoa.dto.StoreMenuDTO;
import com.congcongjoa.congcongjoa.entity.StoreMenu;

@Mapper
public interface StoreMenuMapper {

    StoreMenuMapper INSTANCE = Mappers.getMapper(StoreMenuMapper.class);

    @Mapping(source = "store.id", target = "SIdx")
    @Mapping(source = "menu.id", target = "mnIdx")
    @Mapping(target = "orderDetails", ignore = true)
    StoreMenuDTO toStoreMenuDTO(StoreMenu storeMenu);

    @Mapping(source = "SIdx", target = "store.id")
    @Mapping(source = "mnIdx", target = "menu.id")
    @Mapping(target = "orderDetails", ignore = true)
    StoreMenu toStoreMenu(StoreMenuDTO storeMenuDTO);

    List<StoreMenuDTO> toStoreMenuDTOList(List<StoreMenu> storeMenuList);

    List<StoreMenu> toStoreMenuList(List<StoreMenuDTO> storeMenuDTOList);
    
}
