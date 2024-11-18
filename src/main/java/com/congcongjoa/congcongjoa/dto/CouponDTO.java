package com.congcongjoa.congcongjoa.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.congcongjoa.congcongjoa.entity.enumurate.BooleanStatus;
import com.congcongjoa.congcongjoa.vo.StartEndVo;

@Data
public class CouponDTO {

    private Long id;
    private Long crIdx;
    private String cCode;
    private StartEndVo cStartEnd;
    private Long cCount;
    private BooleanStatus cStatus;
    private LocalDateTime cDate;
    private String cNone;
    private List<UserCouponDTO> userCoupons = new ArrayList<>();   

}
