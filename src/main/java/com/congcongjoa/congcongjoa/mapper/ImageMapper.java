package com.congcongjoa.congcongjoa.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.congcongjoa.congcongjoa.dto.ImageDTO;
import com.congcongjoa.congcongjoa.entity.Image;

@Mapper
public interface ImageMapper {

    ImageMapper INSTANCE = Mappers.getMapper(ImageMapper.class);

    @Mapping(source = "store.id", target = "SIdx")
    @Mapping(source = "menu.id", target = "mnIdx") 
    @Mapping(source = "ICate", target = "ICate")
    @Mapping(source = "IName", target = "IName")
    @Mapping(source = "IMain", target = "IMain")
    @Mapping(source = "IStatus", target = "IStatus")
    @Mapping(source = "INone", target = "INone")
    ImageDTO toImageDTO(Image image);

    @Mapping(source = "SIdx", target = "store.id")
    @Mapping(source = "mnIdx", target = "menu.id") 
    @Mapping(source = "ICate", target = "iCate")
    @Mapping(source = "IName", target = "iName")
    @Mapping(source = "IMain", target = "iMain")
    @Mapping(source = "IStatus", target = "iStatus")
    @Mapping(source = "INone", target = "iNone")
    Image toImage(ImageDTO imageDTO);

    List<ImageDTO> toImageDTOList(List<Image> imageList);

    List<Image> toImageList(List<ImageDTO> imageDTOList);
}
