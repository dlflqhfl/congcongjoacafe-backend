package com.congcongjoa.congcongjoa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.congcongjoa.congcongjoa.repository.CouponRuleRepository;

@Service
public class CouponRuleService {

    @Autowired
    private CouponRuleRepository couponRuleRepository;
    
}
