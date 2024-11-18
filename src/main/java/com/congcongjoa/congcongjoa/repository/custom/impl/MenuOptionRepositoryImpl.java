package com.congcongjoa.congcongjoa.repository.custom.impl;

import com.congcongjoa.congcongjoa.repository.custom.MenuOptionRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class MenuOptionRepositoryImpl implements MenuOptionRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public MenuOptionRepositoryImpl(JPAQueryFactory queryFactory){
        this.queryFactory = queryFactory;
    }
    
}
