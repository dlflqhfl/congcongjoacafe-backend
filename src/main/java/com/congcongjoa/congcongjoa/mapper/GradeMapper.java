package com.congcongjoa.congcongjoa.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.congcongjoa.congcongjoa.dto.GradeDTO;
import com.congcongjoa.congcongjoa.entity.Grade;

@Mapper
public interface GradeMapper {

    GradeMapper INSTANCE = Mappers.getMapper(GradeMapper.class);

    @Mapping(target = "members", ignore = true)
    GradeDTO toGradeDTO(Grade grade);

    @Mapping(target = "members", ignore = true)
    Grade toGrade(GradeDTO gradeDTO);

    List<GradeDTO> toGradeDTOList(List<Grade> gradeList);

    List<Grade> toGradeList(List<GradeDTO> gradeDTOList);
    
}
