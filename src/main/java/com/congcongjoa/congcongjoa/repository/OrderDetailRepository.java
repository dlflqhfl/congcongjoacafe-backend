package com.congcongjoa.congcongjoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.congcongjoa.congcongjoa.entity.OrderDetail;
import com.congcongjoa.congcongjoa.repository.custom.OrderDetailRepositoryCustom;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>, OrderDetailRepositoryCustom {
    
}
