package com.congcongjoa.congcongjoa.dto.custom;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class OwnerDashboardStatsDTO {
    private OrdersDTO orders;
    private RevenueDTO revenue;
    private MenuDTO menu;
    private CustomersDTO customers;

    //오늘의 주문
    @Setter
    @Getter
    public static class OrdersDTO {
        private Long count;
        private Long pending;
    }

    //오늘의 매출
    @Setter
    @Getter
    public static class RevenueDTO {
        private Long total;
        private Double percentage;
    }

    //오늘의 메뉴
    @Setter
    @Getter
    public static class MenuDTO {
        private String name;
        private Long sales;
        private Long revenue;
    }

    //고객문의
    @Setter
    @Getter
    public static class CustomersDTO {
        private Long total;
        private Long unanswered;
    }
}