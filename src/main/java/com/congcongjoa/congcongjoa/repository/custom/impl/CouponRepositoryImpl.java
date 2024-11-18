package com.congcongjoa.congcongjoa.repository.custom.impl;

import com.congcongjoa.congcongjoa.repository.custom.CouponRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class CouponRepositoryImpl implements CouponRepositoryCustom {
    
    private final JPAQueryFactory queryFactory;

    public CouponRepositoryImpl(JPAQueryFactory queryFactory){
        this.queryFactory = queryFactory;
    }
    
}
