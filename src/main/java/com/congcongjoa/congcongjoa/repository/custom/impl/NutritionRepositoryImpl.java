package com.congcongjoa.congcongjoa.repository.custom.impl;

import com.congcongjoa.congcongjoa.repository.custom.NutritionRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class NutritionRepositoryImpl implements NutritionRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public NutritionRepositoryImpl(JPAQueryFactory queryFactory){
        this.queryFactory = queryFactory;
    }
    
}