package com.congcongjoa.congcongjoa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.congcongjoa.congcongjoa.repository.UserCouponRepository;

@Service
public class UserCouponService {

    @Autowired
    private UserCouponRepository userCouponRepository;

}