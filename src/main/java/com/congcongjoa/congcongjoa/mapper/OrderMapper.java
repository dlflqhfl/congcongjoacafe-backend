package com.congcongjoa.congcongjoa.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.congcongjoa.congcongjoa.dto.OrderDTO;
import com.congcongjoa.congcongjoa.entity.Order;

@Mapper
public interface OrderMapper {
    
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "member.id", target = "MIdx")
    @Mapping(target = "orderDetails", ignore = true)
    @Mapping(target = "payments", ignore = true)
    OrderDTO toOrderDTO(Order order);

    @Mapping(source = "MIdx", target = "member.id")
    @Mapping(target = "orderDetails", ignore = true)
    @Mapping(target = "payments", ignore = true)
    Order toOrder(OrderDTO orderDTO);

    List<OrderDTO> toOrderDTOList(List<Order> orderList);

    List<Order> toOrderList(List<OrderDTO> orderDTOList);
    
}
