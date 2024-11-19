package com.congcongjoa.congcongjoa.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.congcongjoa.congcongjoa.enums.BooleanStatus;
import com.congcongjoa.congcongjoa.vo.StartEndVo;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "coupon")
@ToString(exclude = {"userCoupons"})
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_idx", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cr_idx", referencedColumnName = "cr_idx")
    private CouponRule couponRule;
    
    @Column(name = "c_code", nullable = false, length = 200)
    private String cCode;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "start", column = @Column(name = "c_start", nullable = false)),
            @AttributeOverride(name = "end", column = @Column(name = "c_end", nullable = false))
    })
    private StartEndVo cStartEnd;

    @Column(name = "c_count", nullable = false)
    private Long cCount;

    //0: 등록 1: 삭제
    @Enumerated(EnumType.STRING)
    @Column(name = "c_status", nullable = false)
    private BooleanStatus cStatus;

    @Column(name = "c_date")
    private LocalDateTime cDate;

    @Column(name = "c_none", length = 200)
    private String cNone;

    @OneToMany(mappedBy = "coupon" , fetch = FetchType.LAZY)
    private List<UserCoupon> userCoupons = new ArrayList<>();

}
