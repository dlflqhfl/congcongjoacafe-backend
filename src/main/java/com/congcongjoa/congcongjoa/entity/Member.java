package com.congcongjoa.congcongjoa.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.congcongjoa.congcongjoa.enums.BooleanStatus;
import com.congcongjoa.congcongjoa.enums.MemberAuth;
import com.congcongjoa.congcongjoa.enums.Social;
import com.congcongjoa.congcongjoa.vo.AddressVo;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Entity
@Table(name = "member")
@ToString(exclude = {"payments", "userCoupons", "orders", "stamps", "grade"})
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "m_idx", nullable = false)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "g_idx", referencedColumnName = "g_idx")
    private Grade grade;

    @Column(name = "m_name", nullable = false, length = 200)
    private String mName;

    @Column(name = "m_email", nullable = false, length = 200)
    private String mEmail;

    @Column(name = "m_pw", nullable = false, length = 200)
    private String mPw;

    @Column(name = "m_birth", nullable = false, length = 200)
    private LocalDateTime mBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "m_auth", nullable = false)
    private MemberAuth mAuth;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "postCode", column = @Column(name = "m_post", length = 200)),
        @AttributeOverride(name = "street", column = @Column(name = "m_street", length = 200)),
        @AttributeOverride(name = "detail", column = @Column(name = "m_detail", length = 200))
    })
    private AddressVo mAddress;

    @Column(name = "m_phone", length = 100)
    private String mPhone;
    
    @Column(name = "m_access_token", length = 1024)
    private String mAccessToken;

    @Column(name = "m_refresh_token", length = 1024)
    private String mRefreshToken;

    // 0: 이메일 1: 카카오 2: 네이버 3: 구글
    @Enumerated(EnumType.STRING)
    @Column(name = "m_social", nullable = false)
    private Social mSocial;
    
    @Column(name = "m_date", nullable = false, updatable = false)
    private LocalDateTime mDate;

    //0: 동의 1: 비동의
    @Enumerated(EnumType.STRING)
    @Column(name = "m_email_check", nullable = false)
    private BooleanStatus mEmailCheck;

    //0: 가입 1: 탈퇴
    @Enumerated(EnumType.STRING)
    @Column(name = "m_status", nullable = false)
    private BooleanStatus mStatus;

    @Column(name = "m_none", length = 200)
    private String mNone;

    @Builder.Default
    @OneToMany(mappedBy = "member" , fetch = FetchType.LAZY)
    private List<Payment> payments = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member" , fetch = FetchType.LAZY)
    private List<UserCoupon> userCoupons = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member" , fetch = FetchType.LAZY)
    private List<Orders> orders = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member" , fetch = FetchType.LAZY)
    private List<Stamp> stamps = new ArrayList<>();
    
}
