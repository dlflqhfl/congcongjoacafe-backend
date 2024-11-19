package com.congcongjoa.congcongjoa.dto;

import lombok.Data;

import java.time.LocalDateTime;

import com.congcongjoa.congcongjoa.enums.PaymentMethod;

@Data
public class PaymentDTO {

    private Long id;
    private Long orIdx;
    private Long mIdx;
    private String pCode;
    private Long pSales;
    private Long pPrice;
    private PaymentMethod pMethod;
    private LocalDateTime pDate;
    private String pNone;

}
