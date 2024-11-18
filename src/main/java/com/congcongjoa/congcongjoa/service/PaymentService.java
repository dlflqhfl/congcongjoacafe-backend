package com.congcongjoa.congcongjoa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.congcongjoa.congcongjoa.repository.PaymentRepository;

@Service
public class PaymentService {
    
    @Autowired
    private PaymentRepository paymentRepository;
    
}
