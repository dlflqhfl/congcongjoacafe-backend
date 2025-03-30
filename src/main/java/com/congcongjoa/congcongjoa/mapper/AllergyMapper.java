package com.congcongjoa.congcongjoa.mapper;

import com.congcongjoa.congcongjoa.dto.AllergyDTO;
import com.congcongjoa.congcongjoa.entity.Allergy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AllergyMapper {

    AllergyMapper INSTANCE = Mappers.getMapper(AllergyMapper.class);

    @Mapping(source = "menu.id", target = "mnIdx")
    @Mapping(source = "ASoy", target = "ASoy")
    @Mapping(source = "AMilk", target = "AMilk")
    @Mapping(source = "AEgg", target = "AEgg")
    @Mapping(source = "AWheat", target = "AWheat")
    @Mapping(source = "ANone", target = "ANone")
    AllergyDTO toAllergyDTO(Allergy allergy);

    @Mapping(source = "mnIdx", target = "menu.id")
    @Mapping(source = "ASoy", target = "aSoy")
    @Mapping(source = "AMilk", target = "aMilk")
    @Mapping(source = "AEgg", target = "aEgg")
    @Mapping(source = "AWheat", target = "aWheat")
    @Mapping(source = "ANone", target = "aNone")
    Allergy toAllergy(AllergyDTO allergyDTO);

    List<AllergyDTO> toAllergyDTOList(List<Allergy> allergyList);

    List<Allergy> toAllergyList(List<AllergyDTO> allergyDTOList);
}