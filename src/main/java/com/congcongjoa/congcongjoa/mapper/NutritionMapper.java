package com.congcongjoa.congcongjoa.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.congcongjoa.congcongjoa.dto.NutritionDTO;
import com.congcongjoa.congcongjoa.entity.Nutrition;

@Mapper
public interface NutritionMapper {

    NutritionMapper INSTANCE = Mappers.getMapper(NutritionMapper.class);

    @Mapping(source = "menu.id", target = "mnIdx")
    NutritionDTO toNutritionDTO(Nutrition nutrition);

    @Mapping(source = "mnIdx", target = "menu.id")
    Nutrition toNutrition(NutritionDTO nutritionDTO);

    List<NutritionDTO> toNutritionDTOList(List<Nutrition> nutritionList);

    List<Nutrition> toNutritionList(List<NutritionDTO> nutritionDTOList);
    
}
