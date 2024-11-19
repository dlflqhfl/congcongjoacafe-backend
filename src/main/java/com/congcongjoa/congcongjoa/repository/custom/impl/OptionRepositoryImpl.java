package com.congcongjoa.congcongjoa.repository.custom.impl;

import com.congcongjoa.congcongjoa.repository.custom.OptionRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class OptionRepositoryImpl implements OptionRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public OptionRepositoryImpl(JPAQueryFactory queryFactory){
        this.queryFactory = queryFactory;
    }
    
}
