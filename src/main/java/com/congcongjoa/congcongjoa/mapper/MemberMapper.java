package com.congcongjoa.congcongjoa.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.congcongjoa.congcongjoa.dto.MemberDTO;
import com.congcongjoa.congcongjoa.entity.Member;

@Mapper
public interface MemberMapper {

    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    @Mapping(source = "grade.id", target = "GIdx")
    @Mapping(target = "payments", ignore = true)
    @Mapping(target = "userCoupons", ignore = true)
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "stamps", ignore = true)
    MemberDTO toMemberDTO(Member member);

    @Mapping(source = "GIdx", target = "grade.id")
    @Mapping(target = "payments", ignore = true)
    @Mapping(target = "userCoupons", ignore = true)
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "stamps", ignore = true)
    Member toMember(MemberDTO memberDTO);

    List<MemberDTO> toMemberDTOList(List<Member> memberList);

    List<Member> toMemberList(List<MemberDTO> memberDTOList);
    
}
