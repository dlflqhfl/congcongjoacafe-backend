package com.congcongjoa.congcongjoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.congcongjoa.congcongjoa.entity.CouponRule;
import com.congcongjoa.congcongjoa.repository.custom.CouponRuleRepositoryCustom;

public interface CouponRuleRepository extends JpaRepository<CouponRule, Long>, CouponRuleRepositoryCustom {
    
}
