package com.congcongjoa.congcongjoa.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.congcongjoa.congcongjoa.dto.CouponDTO;
import com.congcongjoa.congcongjoa.entity.Coupon;

@Mapper
public interface CouponMapper {

    CouponMapper INSTANCE = Mappers.getMapper(CouponMapper.class);

    @Mapping(source = "couponRule.id", target = "crIdx")
    @Mapping(target = "userCoupons", ignore = true)
    CouponDTO toCouponDTO(Coupon coupon);

    @Mapping(source = "crIdx", target = "couponRule.id")
    @Mapping(target = "userCoupons", ignore = true)
    Coupon toCoupon(CouponDTO couponDTO);

    List<CouponDTO> toCouponDTOList(List<Coupon> couponList);

    List<Coupon> toCouponList(List<CouponDTO> couponDTOList);
    
}
