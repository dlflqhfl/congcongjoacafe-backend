package com.congcongjoa.congcongjoa.dto;

import lombok.Data;

import java.time.LocalDateTime;

import com.congcongjoa.congcongjoa.entity.enumurate.BooleanStatus;
import com.congcongjoa.congcongjoa.vo.StartEndVo;

@Data
public class UserCouponDTO {

    private Long id;
    private Long cIdx;
    private Long mIdx;
    private StartEndVo ucStartEnd;
    private LocalDateTime ucDate;
    private BooleanStatus ucStatus;
    private String ucNone;

}
