package com.congcongjoa.congcongjoa.repository.custom.impl;

import com.congcongjoa.congcongjoa.repository.custom.GradeRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class GradeRepositoryImpl implements GradeRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public GradeRepositoryImpl(JPAQueryFactory queryFactory){
        this.queryFactory = queryFactory;
    }
    
}
