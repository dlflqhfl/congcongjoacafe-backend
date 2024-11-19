package com.congcongjoa.congcongjoa.repository.custom.impl;

import com.congcongjoa.congcongjoa.repository.custom.ImageRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class ImageRepositoryImpl implements ImageRepositoryCustom {
    
    private final JPAQueryFactory queryFactory;

    public ImageRepositoryImpl(JPAQueryFactory queryFactory){
        this.queryFactory = queryFactory;
    }
    
}
