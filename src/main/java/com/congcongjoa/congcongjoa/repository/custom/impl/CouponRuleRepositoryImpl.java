package com.congcongjoa.congcongjoa.repository.custom.impl;

import com.congcongjoa.congcongjoa.repository.custom.CouponRuleRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class CouponRuleRepositoryImpl implements CouponRuleRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public CouponRuleRepositoryImpl(JPAQueryFactory queryFactory){
        this.queryFactory = queryFactory;
    }
    
}