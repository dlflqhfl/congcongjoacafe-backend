package com.congcongjoa.congcongjoa.entity;

import java.time.LocalDateTime;

import com.congcongjoa.congcongjoa.enums.PaymentMethod;

import jakarta.persistence.Column;
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

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Entity
@Table(name = "payment")
@ToString(exclude = {"order", "member"})
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_idx", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "or_idx", referencedColumnName = "or_idx")
    private Orders orders;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "m_idx", referencedColumnName = "m_idx")
    private Member member;

    @Column(name = "p_code", length = 200)
    private String pCode;
    
    @Column(name = "p_sales")
    private Long pSales;

    @Column(name = "p_price")
    private Long pPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "p_method")
    private PaymentMethod pMethod;

    @Column(name = "p_date")
    private LocalDateTime pDate;

    @Column(name = "p_none", length = 200)
    private String pNone;
    
}
