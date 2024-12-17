package com.congcongjoa.congcongjoa.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.congcongjoa.congcongjoa.dto.MemberDTO;
import com.congcongjoa.congcongjoa.dto.custom.MemberListDTO;
import com.congcongjoa.congcongjoa.entity.Member;
import com.congcongjoa.congcongjoa.enums.BooleanStatus;
import com.congcongjoa.congcongjoa.mapper.MemberMapper;
import com.congcongjoa.congcongjoa.repository.MemberRepository;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public boolean isEmailDuplicate(String email){
        boolean isDuplicate = memberRepository.existsByMEmailAndMStatus(email, BooleanStatus.TRUE);

        return isDuplicate;
    }

    public List<MemberListDTO> getMemberList() {
        List<Member> members = memberRepository.findUserAll();

        List<MemberListDTO> memberListDTO = members.stream()
                .map(member -> {
                    MemberListDTO dto = new MemberListDTO();
                    dto.setId(member.getId());
                    dto.setGIdx(member.getGrade().getId());
                    dto.setGName(member.getGrade().getGName());
                    dto.setMName(member.getMName());
                    dto.setMEmail(member.getMEmail());
                    dto.setMPhone(member.getMPhone());
                    dto.setMBirth(member.getMBirth());
                    dto.setMDate(member.getMDate());
                    dto.setMStatus(member.getMStatus());
                    return dto;
                })
                .collect(Collectors.toList());
        return memberListDTO;
    }
    
}
