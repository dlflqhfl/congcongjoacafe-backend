package com.congcongjoa.congcongjoa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.congcongjoa.congcongjoa.repository.CouponRepository;

@Service
public class CouponService {
    
    @Autowired
    private CouponRepository couponRepository;
    
}
