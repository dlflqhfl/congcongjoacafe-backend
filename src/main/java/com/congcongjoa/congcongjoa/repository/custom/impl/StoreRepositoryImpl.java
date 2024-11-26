package com.congcongjoa.congcongjoa.repository.custom.impl;


import com.congcongjoa.congcongjoa.dto.QStoreDTO;
import com.congcongjoa.congcongjoa.dto.StoreDTO;
import com.congcongjoa.congcongjoa.repository.custom.StoreRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;

import static com.congcongjoa.congcongjoa.entity.QStore.store;


public class StoreRepositoryImpl implements StoreRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public StoreRepositoryImpl(JPAQueryFactory queryFactory){
        this.queryFactory = queryFactory;
    }

    @Override
    public List<StoreDTO> findSName() {

        return queryFactory
                .select(new QStoreDTO(store.sName))
                .from(store)
                .fetch();

    }
}
