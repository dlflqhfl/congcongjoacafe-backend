package com.congcongjoa.congcongjoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.congcongjoa.congcongjoa.entity.Payment;
import com.congcongjoa.congcongjoa.repository.custom.PaymentRepositoryCustom;

public interface PaymentRepository extends JpaRepository<Payment, Long>, PaymentRepositoryCustom {
    
}
