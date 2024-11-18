package com.congcongjoa.congcongjoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.congcongjoa.congcongjoa.entity.Member;
import com.congcongjoa.congcongjoa.repository.custom.MemberRepositoryCustom;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {
    
}
