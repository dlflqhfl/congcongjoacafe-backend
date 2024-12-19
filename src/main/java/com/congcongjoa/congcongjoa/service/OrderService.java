package com.congcongjoa.congcongjoa.service;

import com.congcongjoa.congcongjoa.dto.custom.OwnerDashboardStatsDTO;
import com.congcongjoa.congcongjoa.entity.OrderDetail;
import com.congcongjoa.congcongjoa.entity.QOrderDetail;
import com.querydsl.core.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.congcongjoa.congcongjoa.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

import static com.congcongjoa.congcongjoa.entity.QOrderDetail.orderDetail;

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
            Long waitingCount = orderRepository.getWaitingCount(id);
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

    public List<OwnerDashboardStatsDTO.MenuDTO> getTodayMenus(Long id) {
        try {
            List<OwnerDashboardStatsDTO.MenuDTO> menuDTOList = new ArrayList<OwnerDashboardStatsDTO.MenuDTO>();

            List< Tuple> menuList  = orderRepository.getTodayBestSellingMenu(id);

            if(menuList != null && menuList.size() > 0) {
                menuList.forEach(tuple -> {
                    OwnerDashboardStatsDTO.MenuDTO menuDTO = new OwnerDashboardStatsDTO.MenuDTO();
                    menuDTO.setName(tuple.get(orderDetail.odName));
                    menuDTO.setSales(tuple.get(orderDetail.count()));
                    menuDTO.setRevenue(tuple.get(orderDetail.odPrice));
                    menuDTOList.add(menuDTO);
                });
            }

            return menuDTOList;
        }catch (Exception e){
            System.err.println("Error calculating today's menus: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
