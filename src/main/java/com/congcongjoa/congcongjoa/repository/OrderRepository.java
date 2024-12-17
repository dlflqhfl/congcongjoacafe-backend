package com.congcongjoa.congcongjoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.congcongjoa.congcongjoa.entity.Orders;
import com.congcongjoa.congcongjoa.repository.custom.OrderRepositoryCustom;

public interface OrderRepository extends JpaRepository<Orders, Long>, OrderRepositoryCustom {
    
}
