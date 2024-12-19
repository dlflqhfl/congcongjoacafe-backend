package com.congcongjoa.congcongjoa.repository.custom;

import com.querydsl.core.Tuple;

import java.util.List;

public interface OrderRepositoryCustom {
    Long getTodayTotalOrders(Long id);

    Long getWaitingCount(Long id);

    Long getTodayRevenue(Long id);

    Long getYesterdayRevenue(Long id);

    List<Tuple> getTodayBestSellingMenu(Long id);
}
