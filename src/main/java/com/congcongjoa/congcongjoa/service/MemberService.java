package com.congcongjoa.congcongjoa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.congcongjoa.congcongjoa.entity.enumurate.BooleanStatus;
import com.congcongjoa.congcongjoa.repository.MemberRepository;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public boolean isEmailDuplicate(String email){
        boolean isDuplicate = memberRepository.existsByMEmailAndMStatus(email, BooleanStatus.TRUE);

        return isDuplicate;
    }
    
}
