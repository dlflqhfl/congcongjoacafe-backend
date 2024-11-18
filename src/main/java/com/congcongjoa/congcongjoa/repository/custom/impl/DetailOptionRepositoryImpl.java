package com.congcongjoa.congcongjoa.repository.custom.impl;

import com.congcongjoa.congcongjoa.repository.custom.DetailOptionRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class DetailOptionRepositoryImpl implements DetailOptionRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public DetailOptionRepositoryImpl(JPAQueryFactory queryFactory){
        this.queryFactory = queryFactory;
    }
    
}