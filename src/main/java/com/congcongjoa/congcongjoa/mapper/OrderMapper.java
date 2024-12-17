package com.congcongjoa.congcongjoa.mapper;

import java.util.List;

import com.congcongjoa.congcongjoa.entity.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.congcongjoa.congcongjoa.dto.OrderDTO;

@Mapper
public interface OrderMapper {
    
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "member.id", target = "MIdx")
    @Mapping(target = "orderDetails", ignore = true)
    @Mapping(target = "payments", ignore = true)
    OrderDTO toOrderDTO(Orders orders);

    @Mapping(source = "MIdx", target = "member.id")
    @Mapping(target = "orderDetails", ignore = true)
    @Mapping(target = "payments", ignore = true)
    Orders toOrder(OrderDTO orderDTO);

    List<OrderDTO> toOrderDTOList(List<Orders> ordersList);

    List<Orders> toOrderList(List<OrderDTO> orderDTOList);
    
}
