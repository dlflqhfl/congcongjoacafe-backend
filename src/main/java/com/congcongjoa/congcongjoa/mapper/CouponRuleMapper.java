package com.congcongjoa.congcongjoa.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.congcongjoa.congcongjoa.dto.CouponRuleDTO;
import com.congcongjoa.congcongjoa.entity.CouponRule;

@Mapper
public interface CouponRuleMapper {

    CouponRuleMapper INSTANCE = Mappers.getMapper(CouponRuleMapper.class);

    @Mapping(target = "coupons", ignore = true)
    CouponRuleDTO toCouponRuleDTO(CouponRule couponRule);

    @Mapping(target = "coupons", ignore = true)
    CouponRule toCouponRule(CouponRuleDTO couponRuleDTO);

    List<CouponRuleDTO> toCouponRuleDTOList(List<CouponRule> couponRuleList);

    List<CouponRule> toCouponRuleList(List<CouponRuleDTO> couponRuleDTOList);
    
}
