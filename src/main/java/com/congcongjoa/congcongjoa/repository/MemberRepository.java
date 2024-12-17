package com.congcongjoa.congcongjoa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.congcongjoa.congcongjoa.entity.Member;
import com.congcongjoa.congcongjoa.enums.BooleanStatus;
import com.congcongjoa.congcongjoa.repository.custom.MemberRepositoryCustom;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {
    @Query("select count(m) > 0 from Member m where m.mEmail = :mEmail and m.mStatus = :mStatus")
    boolean existsByMEmailAndMStatus(String mEmail, BooleanStatus mStatus);

    @Query("SELECT m FROM Member m WHERE m.mAuth = com.congcongjoa.congcongjoa.enums.MemberAuth.USER")
    List<Member> findUserAll();

}
