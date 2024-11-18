package com.congcongjoa.congcongjoa.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.congcongjoa.congcongjoa.dto.OrderDetailDTO;
import com.congcongjoa.congcongjoa.entity.OrderDetail;

@Mapper
public interface OrderDetailMapper {

    OrderDetailMapper INSTANCE = Mappers.getMapper(OrderDetailMapper.class);

    @Mapping(source = "order.id", target = "orIdx")
    @Mapping(source = "storeMenu.id", target = "smIdx")
    OrderDetailDTO toOrderDetailDTO(OrderDetail orderDetail);

    @Mapping(source = "orIdx", target = "order.id")
    @Mapping(source = "smIdx", target = "storeMenu.id")
    OrderDetail toOrderDetail(OrderDetailDTO orderDetailDTO);

    List<OrderDetailDTO> toOrderDetailDTOList(List<OrderDetail> orderDetailList);

    List<OrderDetail> toOrderDetailList(List<OrderDetailDTO> orderDetailDTOList);
    
}
