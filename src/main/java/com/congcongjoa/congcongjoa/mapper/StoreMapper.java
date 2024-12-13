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

    @Mapping(source = "SDirections", target = "directions")
    @Mapping(source = "SCode", target = "SCode")
    @Mapping(source = "SPw" , target = "SPw")
    @Mapping(source = "SName", target = "SName")
    @Mapping(source = "SAddress", target = "SAddress")
    @Mapping(source = "SPhone", target = "SPhone")
    @Mapping(source = "XAxis" , target = "XAxis")
    @Mapping(source = "YAxis" , target = "YAxis")
    @Mapping(source = "SStartEnd", target =  "SStartEnd")
    @Mapping(source = "SDriveThru", target =  "SDriveThru")
    @Mapping(source = "SPark", target =  "SPark")
    @Mapping(source = "SStoreUse", target =  "SStoreUse")
    @Mapping(source = "SWifi", target =  "SWifi")
    @Mapping(source = "SStatus", target =  "SStatus")
    @Mapping(source = "SNone", target =  "SNone")
    @Mapping(target = "storeMenus", ignore = true)
    @Mapping(target = "images", ignore = true)
    StoreDTO toStoreDTO(Store store);

    @Mapping(source = "directions", target = "sDirections")
    @Mapping(source = "SCode", target = "sCode")
    @Mapping(source = "SPw" , target = "sPw")
    @Mapping(source = "SName", target = "sName")
    @Mapping(source = "SAddress", target = "sAddress")
    @Mapping(source = "SPhone", target = "sPhone")
    @Mapping(source = "XAxis" , target = "xAxis")
    @Mapping(source = "YAxis" , target = "yAxis")
    @Mapping(source = "SStartEnd", target =  "sStartEnd")
    @Mapping(source = "SDriveThru", target =  "sDriveThru")
    @Mapping(source = "SPark", target =  "sPark")
    @Mapping(source = "SStoreUse", target =  "sStoreUse")
    @Mapping(source = "SWifi", target =  "sWifi")
    @Mapping(source = "SStatus", target =  "sStatus")
    @Mapping(source = "SNone", target =  "sNone")
    @Mapping(target = "storeMenus", ignore = true)
    @Mapping(target = "images", ignore = true)
    Store toStore(StoreDTO storeDTO);

    List<StoreDTO> toStoreDTOList(List<Store> storeList);

    List<Store> toStoreList(List<StoreDTO> storeDTOList);
}