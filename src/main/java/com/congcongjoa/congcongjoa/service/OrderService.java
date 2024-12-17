package com.congcongjoa.congcongjoa.service;

import com.congcongjoa.congcongjoa.dto.custom.OwnerDashboardStatsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.congcongjoa.congcongjoa.repository.OrderRepository;

import java.text.DecimalFormat;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;


    public OwnerDashboardStatsDTO.OrdersDTO getTodayOrder(Long id) {
        try {
            // DTO 생성
            OwnerDashboardStatsDTO.OrdersDTO ordersDTO = new OwnerDashboardStatsDTO.OrdersDTO();

            // 주문 총 개수 조회
            Long totalCount = orderRepository.getTodayTotalOrders(id);
            ordersDTO.setCount(Long.valueOf(totalCount != null ? totalCount.intValue() : null)); // null-safe

            // 대기 중 주문 개수 조회
            Long waitingCount = orderRepository.waitingCount(id);
            ordersDTO.setPending(Long.valueOf(waitingCount != null ? waitingCount.intValue() : null)); // null-safe

            return ordersDTO;

        } catch (Exception e) {
            // 모든 예외 처리 (로그 출력 및 null 반환)
            e.printStackTrace();
            return null;
        }
    }


    public OwnerDashboardStatsDTO.RevenueDTO getTodayRevenue(Long id) {
        try {
            OwnerDashboardStatsDTO.RevenueDTO revenueDTO = new OwnerDashboardStatsDTO.RevenueDTO();
            Long todayRevenue = orderRepository.getTodayRevenue(id);
            Long yesterdayRevenue = orderRepository.getYesterdayRevenue(id);

            // 초기화
            revenueDTO.setTotal(todayRevenue != null ? todayRevenue : 0L);
            revenueDTO.setPercentage(0.0);

            // 어제 매출이 유효한 경우만 계산 실행
            if (yesterdayRevenue != null && yesterdayRevenue > 0 && todayRevenue != null) {
                double percentageChange = Math.round(((double) (todayRevenue - yesterdayRevenue) / yesterdayRevenue) * 100 * 100) / 100.0;
                revenueDTO.setPercentage(percentageChange);
            }

            return revenueDTO;
        } catch (Exception e) {
            // 에러 처리 개선
            System.err.println("Error calculating today's revenue: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public OwnerDashboardStatsDTO.MenuDTO getTodayMenus(Long id) {


        return null;
    }
}
