package com.congcongjoa.congcongjoa.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.congcongjoa.congcongjoa.dto.AllergyDTO;
import com.congcongjoa.congcongjoa.entity.Allergy;

@Mapper
public interface AllergyMapper {

    AllergyMapper INSTANCE = Mappers.getMapper(AllergyMapper.class);

    @Mapping(source = "menu.id", target = "mnIdx")
    AllergyDTO toAllergyDTO(Allergy allergy);

    @Mapping(source = "mnIdx", target = "menu.id")
    Allergy toAllergy(AllergyDTO allergyDTO);

    List<AllergyDTO> toAllergyDTOList(List<Allergy> allergyList);

    List<Allergy> toAllergyList(List<AllergyDTO> allergyDTOList);
    
}
