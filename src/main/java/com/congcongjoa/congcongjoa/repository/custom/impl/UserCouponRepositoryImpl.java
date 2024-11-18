package com.congcongjoa.congcongjoa.repository.custom.impl;

import com.congcongjoa.congcongjoa.repository.custom.UserCouponRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class UserCouponRepositoryImpl implements UserCouponRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public UserCouponRepositoryImpl(JPAQueryFactory queryFactory){
        this.queryFactory = queryFactory;
    }
    
}
