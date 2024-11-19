package com.congcongjoa.congcongjoa.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.congcongjoa.congcongjoa.entity.enumurate.OrderStatus;

@Data
public class OrderDTO {

    private Long id;
    private Long mIdx;
    private OrderStatus orStatus;
    private LocalDateTime orDate;
    private String orNone;
    private List<OrderDetailDTO> orderDetails = new ArrayList<>();
    private List<PaymentDTO> payments = new ArrayList<>();

}
