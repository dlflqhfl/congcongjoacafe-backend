package com.congcongjoa.congcongjoa.entity;

import java.time.LocalDateTime;

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
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_coupon")
@ToString(exclude = {"coupon", "member"})
public class UserCoupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uc_idx", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "c_idx", referencedColumnName = "c_idx")
    private Coupon coupon;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "m_idx", referencedColumnName = "m_idx")
    private Member member;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "start", column = @Column(name = "uc_issued", nullable = false)),
            @AttributeOverride(name = "end", column = @Column(name = "uc_end", nullable = false))
    })
    private StartEndVo ucStartEnd;
    
    @Column(name = "uc_date")
    private LocalDateTime ucDate;

    //0: 사용가능 1: 사용함
    @Enumerated(EnumType.STRING)
    @Column(name = "uc_status", nullable = false)
    private BooleanStatus ucStatus;

    @Column(name = "uc_none", length = 200)
    private String ucNone;

}
