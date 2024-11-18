package com.congcongjoa.congcongjoa.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.congcongjoa.congcongjoa.dto.StampDTO;
import com.congcongjoa.congcongjoa.entity.Stamp;

@Mapper
public interface StampMapper {

    StampMapper INSTANCE = Mappers.getMapper(StampMapper.class);

    @Mapping(source = "member.id", target = "MIdx")
    StampDTO toStampDTO(Stamp stamp);

    @Mapping(source = "MIdx", target = "member.id")
    Stamp toStamp(StampDTO stampDTO);

    List<StampDTO> toStampDTOList(List<Stamp> stampList);

    List<Stamp> toStampList(List<StampDTO> stampDTOList);
    
}
