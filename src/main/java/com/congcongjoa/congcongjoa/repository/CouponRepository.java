package com.congcongjoa.congcongjoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.congcongjoa.congcongjoa.entity.Coupon;
import com.congcongjoa.congcongjoa.repository.custom.CouponRepositoryCustom;

public interface CouponRepository extends JpaRepository<Coupon, Long>, CouponRepositoryCustom {
    
}
