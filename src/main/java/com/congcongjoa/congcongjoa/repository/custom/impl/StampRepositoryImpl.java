package com.congcongjoa.congcongjoa.repository.custom.impl;

import com.congcongjoa.congcongjoa.repository.custom.StampRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class StampRepositoryImpl implements StampRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public StampRepositoryImpl(JPAQueryFactory queryFactory){
        this.queryFactory = queryFactory;
    }
    
}
