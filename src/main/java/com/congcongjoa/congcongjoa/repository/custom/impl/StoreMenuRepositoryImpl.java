package com.congcongjoa.congcongjoa.repository.custom.impl;

import com.congcongjoa.congcongjoa.repository.custom.StoreMenuRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class StoreMenuRepositoryImpl implements StoreMenuRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public StoreMenuRepositoryImpl(JPAQueryFactory queryFactory){
        this.queryFactory = queryFactory;
    }
    
}
