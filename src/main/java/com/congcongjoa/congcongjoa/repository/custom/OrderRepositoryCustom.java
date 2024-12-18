package com.congcongjoa.congcongjoa.repository.custom;

public interface OrderRepositoryCustom {
    Long getTodayTotalOrders(Long id);

    Long waitingCount(Long id);

    Long getTodayRevenue(Long id);

    Long getYesterdayRevenue(Long id);

    String getTodayBestSellingMenu(Long id);
}
