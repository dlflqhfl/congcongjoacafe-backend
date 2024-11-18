package com.congcongjoa.congcongjoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.congcongjoa.congcongjoa.entity.UserCoupon;
import com.congcongjoa.congcongjoa.repository.custom.UserCouponRepositoryCustom;

public interface UserCouponRepository extends JpaRepository<UserCoupon, Long>, UserCouponRepositoryCustom {
    
}
