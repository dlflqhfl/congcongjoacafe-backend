package com.congcongjoa.congcongjoa.repository.custom.impl;

import com.congcongjoa.congcongjoa.enums.OrderStatus;
import com.congcongjoa.congcongjoa.repository.custom.OrderRepositoryCustom;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.congcongjoa.congcongjoa.entity.QOrderDetail.orderDetail;
import static com.congcongjoa.congcongjoa.entity.QOrders.orders;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    private static final LocalDateTime START_OF_DAY = LocalDateTime.now().with(LocalTime.MIN);
    private static final LocalDateTime END_OF_DAY = LocalDateTime.now().with(LocalTime.MAX);

    public OrderRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public Long getTodayTotalOrders(Long id) {
        return countOrdersByStatus(id, OrderStatus.CANCLE, false);
    }

    @Override
    public Long getWaitingCount(Long id) {
        return countOrdersByStatus(id, OrderStatus.NOTORDER, true);
    }

    @Override
    public Long getTodayRevenue(Long id) {
        return calculateRevenue(id, 0);
    }

    @Override
    public Long getYesterdayRevenue(Long id) {
        return calculateRevenue(id, 1);
    }

    @Override
    public List<Tuple> getTodayBestSellingMenu(Long id) {


        return queryFactory.select(orderDetail.odName, orderDetail.count(), orderDetail.odTotal.sum())
                .from(orderDetail)
                .join(orderDetail.orders, orders)
                .where(orders.orDate.between(START_OF_DAY, END_OF_DAY)
                        .and(orders.store.id.eq(id)
                                .and(orders.orStatus.eq(OrderStatus.COMPLETE))))
                .groupBy(orderDetail.odName)
                .orderBy(orderDetail.odTotal.sum().desc())
                .limit(3)
                .fetch();
    }

    private Long countOrdersByStatus(Long id, OrderStatus status, boolean isEqual) {
        return queryFactory.select(orders.count())
                .from(orders)
                .where(orders.orDate.between(START_OF_DAY, END_OF_DAY)
                        .and(id == null ? orders.id.isNull() : orders.id.eq(id)) // null 처리
                        .and(isEqual ? orders.orStatus.eq(status) : orders.orStatus.ne(status)))
                .fetchOne();
    }

    private Long calculateRevenue(Long id, long daysOffset) {
        return queryFactory.select(orderDetail.odTotal.sum())
                .from(orderDetail)
                .join(orders).on(orderDetail.orders.eq(orders))
                .where(orders.orDate.between(START_OF_DAY.minusDays(daysOffset), END_OF_DAY.minusDays(daysOffset))
                        .and(id == null ? orders.store.id.isNull() : orders.store.id.eq(id)) // null 처리
                        .and(orders.orStatus.eq(OrderStatus.COMPLETE)))
                .fetchOne();
    }
}