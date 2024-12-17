package com.congcongjoa.congcongjoa.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

    private final SimpMessagingTemplate messagingTemplate;
    private final StoreService storeService;
    private final OrderService orderService;

    public WebSocketService(SimpMessagingTemplate messagingTemplate, StoreService storeService, OrderService orderService) {
        this.messagingTemplate = messagingTemplate;
        this.storeService = storeService;
        this.orderService = orderService;
    }

   /* public void pushRealTimeUpdates(String sName) {
        Long sIdx = storeService.getsIdBySName(sName);

        // 실시간 데이터를 전송
        RealTimeStatsDTO stats = new RealTimeStatsDTO();
        stats.setOrders(orderService.getTodayOrder(sIdx)); // 오늘 주문 수 실시간 갱신
        stats.setRevenue(orderService.getTodayRevenue(sIdx)); // 오늘 매출 실시간 갱신

        // WebSocket을 통해 실시간 전송
        messagingTemplate.convertAndSend("/topic/realtime-stats", stats);
    }*/

}
