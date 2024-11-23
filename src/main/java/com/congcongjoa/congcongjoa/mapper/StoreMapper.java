package com.congcongjoa.congcongjoa.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.congcongjoa.congcongjoa.dto.StoreDTO;
import com.congcongjoa.congcongjoa.entity.Store;

@Mapper
public interface StoreMapper {

    StoreMapper INSTANCE = Mappers.getMapper(StoreMapper.class);

    @Mapping(target = "storeMenus", ignore = true)
    @Mapping(target = "images", ignore = true)
    StoreDTO toStoreDTO(Store store);

    @Mapping(target = "storeMenus", ignore = true)
    @Mapping(target = "images", ignore = true)
    Store toStore(StoreDTO storeDTO);

    List<StoreDTO> toStoreDTOList(List<Store> storeList);

    List<Store> toStoreList(List<StoreDTO> storeDTOList);
}