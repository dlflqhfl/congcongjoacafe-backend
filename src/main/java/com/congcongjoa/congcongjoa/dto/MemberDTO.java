package com.congcongjoa.congcongjoa.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.congcongjoa.congcongjoa.enums.BooleanStatus;
import com.congcongjoa.congcongjoa.enums.MemberAuth;
import com.congcongjoa.congcongjoa.enums.Social;
import com.congcongjoa.congcongjoa.vo.AddressVo;

@Data
public class MemberDTO {

    private Long id;
    private Long gIdx;
    private String mName;
    private String mEmail;
    private String mPw;
    private LocalDateTime mBirth;
    private MemberAuth mAuth;
    private AddressVo mAddress;
    private String mPhone;
    private String mAccessToken;
    private String mRefreshToken;
    private Social mSocial;
    private LocalDateTime mDate;
    private BooleanStatus mEmailCheck;
    private BooleanStatus mStatus;
    private String mNone;
    private List<PaymentDTO> payments = new ArrayList<>();
    private List<UserCouponDTO> userCoupons = new ArrayList<>();
    private List<OrderDTO> orders = new ArrayList<>();
    private List<StampDTO> stamps = new ArrayList<>();

}
