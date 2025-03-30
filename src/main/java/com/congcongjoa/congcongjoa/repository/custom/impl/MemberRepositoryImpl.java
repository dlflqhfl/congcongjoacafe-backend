package com.congcongjoa.congcongjoa.repository.custom.impl;

import com.congcongjoa.congcongjoa.entity.Member;
import com.congcongjoa.congcongjoa.entity.QMember;
import com.congcongjoa.congcongjoa.enums.MemberAuth;
import com.congcongjoa.congcongjoa.repository.custom.MemberRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.congcongjoa.congcongjoa.entity.QMember.*;

public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public MemberRepositoryImpl(JPAQueryFactory queryFactory){
        this.queryFactory = queryFactory;
    }

    @Override
    public Member findByUserName(String username, String role) {
        MemberAuth memberAuth = "ROLE_ADMIN".equals(role) ? MemberAuth.ADMIN : MemberAuth.USER;
        return findMemberByRoleAndUsername(username, memberAuth);
    }

    private Member findMemberByRoleAndUsername(String username, MemberAuth memberAuth) {
        return queryFactory
                .selectFrom(member)
                .where(member.mEmail.eq(username),
                        member.mAuth.eq(memberAuth))
                .fetchOne();
    }
}
