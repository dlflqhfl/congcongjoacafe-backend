package com.congcongjoa.congcongjoa.mapper;

import com.congcongjoa.congcongjoa.dto.StoreDTO;
import com.congcongjoa.congcongjoa.entity.Store;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface StoreMapper {

    StoreMapper INSTANCE = Mappers.getMapper(StoreMapper.class);

    @Mapping(source = "SDirections", target = "directions") // 필드 매핑 정확성 확인
    @Mapping(source = "images", target = "images")          // images 필드 매핑
    @Mapping(target = "storeMenus", ignore = true)          // storeMenus는 무시 처리
    StoreDTO toStoreDTO(Store store);

    @Mapping(source = "directions", target = "sDirections") // 역매핑
    Store toStore(StoreDTO storeDTO);

    List<StoreDTO> toStoreDTOList(List<Store> storeList);

    List<Store> toStoreList(List<StoreDTO> storeDTOList);
}