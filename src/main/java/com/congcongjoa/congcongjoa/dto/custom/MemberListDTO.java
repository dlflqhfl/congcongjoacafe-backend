package com.congcongjoa.congcongjoa.dto.custom;

import java.time.LocalDateTime;

import com.congcongjoa.congcongjoa.enums.BooleanStatus;

import lombok.Data;

@Data
public class MemberListDTO {

    private Long id;
    private Long gIdx;
    private String gName;
    private String mName;
    private String mEmail;
    private String mPhone;
    private LocalDateTime mBirth;
    private LocalDateTime mDate;
    private BooleanStatus mStatus;

}
