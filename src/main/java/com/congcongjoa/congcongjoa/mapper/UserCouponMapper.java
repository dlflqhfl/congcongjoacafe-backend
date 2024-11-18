package com.congcongjoa.congcongjoa.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.congcongjoa.congcongjoa.dto.UserCouponDTO;
import com.congcongjoa.congcongjoa.entity.UserCoupon;

@Mapper
public interface UserCouponMapper {

    UserCouponMapper INSTANCE = Mappers.getMapper(UserCouponMapper.class);

    @Mapping(source = "coupon.id", target = "CIdx")
    @Mapping(source = "member.id", target = "MIdx")
    UserCouponDTO toUserCouponDTO(UserCoupon userCoupon);

    @Mapping(source = "CIdx", target = "coupon.id")
    @Mapping(source = "MIdx", target = "member.id")
    UserCoupon toUserCoupon(UserCouponDTO userCouponDTO);

    List<UserCouponDTO> toUserCouponDTOList(List<UserCoupon> userCouponList);

    List<UserCoupon> toUserCouponList(List<UserCouponDTO> userCouponDTOList);
    
}
