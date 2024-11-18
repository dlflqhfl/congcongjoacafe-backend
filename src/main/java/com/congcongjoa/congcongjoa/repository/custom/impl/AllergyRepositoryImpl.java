package com.congcongjoa.congcongjoa.repository.custom.impl;

import com.congcongjoa.congcongjoa.repository.custom.AllergyRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class AllergyRepositoryImpl implements AllergyRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public AllergyRepositoryImpl(JPAQueryFactory queryFactory){
        this.queryFactory = queryFactory;
    }
    
}
