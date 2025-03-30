package com.congcongjoa.congcongjoa.repository.custom;

import com.congcongjoa.congcongjoa.entity.Member;

public interface MemberRepositoryCustom {

    Member findByUserName(String username, String role);
}
