package com.congcongjoa.congcongjoa.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import com.congcongjoa.congcongjoa.enums.CpCate;
import com.congcongjoa.congcongjoa.enums.CpMethod;

@Data
public class CouponRuleDTO {

    private Long id;
    private String crName;
    private CpCate cpCate;
    private Long crPrice;
    private CpMethod cpMethod;
    private Long crTarget;
    private String crNone;
    private List<CouponDTO> coupons = new ArrayList<>();
}