package com.congcongjoa.congcongjoa.service;


import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.congcongjoa.congcongjoa.dto.MemberDTO;
import com.congcongjoa.congcongjoa.entity.Member;
import com.congcongjoa.congcongjoa.enums.BooleanStatus;
import com.congcongjoa.congcongjoa.enums.MemberAuth;
import com.congcongjoa.congcongjoa.enums.Social;
import com.congcongjoa.congcongjoa.mapper.MemberMapper;
import com.congcongjoa.congcongjoa.repository.MemberRepository;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public boolean isEmailDuplicate(String email){
        boolean isDuplicate = memberRepository.existsByMEmailAndMStatus(email, BooleanStatus.TRUE);

        return isDuplicate;
    }

    public boolean registerMember(MemberDTO memberDTO){
        boolean isComplete = false;

        memberDTO.setMBirth(LocalDateTime.now().minusMonths(1)); //임시
        memberDTO.setMAuth(MemberAuth.USER);
        memberDTO.setGIdx((long)1);
        memberDTO.setMDate(LocalDateTime.now());
        memberDTO.setMSocial(Social.EMAIL);
        memberDTO.setMStatus(BooleanStatus.TRUE);
        memberDTO.setMPw(passwordEncoder.encode(memberDTO.getMPw()));
        Member member = (Member) MemberMapper.INSTANCE.toMember(memberDTO);

        if(member != null)
            member = memberRepository.save(member);
        
        return isComplete;
    }
    
}
