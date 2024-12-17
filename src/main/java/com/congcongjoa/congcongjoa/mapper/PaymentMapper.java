package com.congcongjoa.congcongjoa.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.congcongjoa.congcongjoa.dto.PaymentDTO;
import com.congcongjoa.congcongjoa.entity.Payment;

@Mapper
public interface PaymentMapper {

    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    @Mapping(source = "orders.id", target = "orIdx")
    @Mapping(source = "member.id", target = "MIdx")
    PaymentDTO toPaymentDTO(Payment payment);

    @Mapping(source = "orIdx", target = "orders.id")
    @Mapping(source = "MIdx", target = "member.id")
    Payment toPayment(PaymentDTO paymentDTO);

    List<PaymentDTO> toPaymentDTOList(List<Payment> paymentList);

    List<Payment> toPaymentList(List<PaymentDTO> paymentDTOList);
    
}
