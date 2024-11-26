package com.congcongjoa.congcongjoa.entity;

import java.util.ArrayList;
import java.util.List;

import com.congcongjoa.congcongjoa.enums.CpCate;
import com.congcongjoa.congcongjoa.enums.CpMethod;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Entity
@Table(name = "coupon_rule")
@ToString(exclude = {"coupons"})
public class CouponRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cr_idx", nullable = false)
    private Long id;

    @Column(name = "cr_name", nullable = false, length = 200)
    private String crName;

    @Enumerated(EnumType.STRING)
    @Column(name = "cp_cate", nullable = false)
    private CpCate cpCate;

    @Column(name = "cr_price", nullable = false)
    private Long crPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "cp_method", nullable = false)
    private CpMethod cpMethod;

    //NULL 일 경우 적용대상 전체 OR 메뉴코드
    @Column(name = "cr_target")
    private Long crTarget;
    
    @Column(name = "cr_none", length = 200)
    private String crNone;

    @Builder.Default
    @OneToMany(mappedBy = "couponRule" , fetch = FetchType.LAZY)
    private List<Coupon> coupons = new ArrayList<>();
    
}
