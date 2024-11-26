package com.congcongjoa.congcongjoa.entity;

import com.congcongjoa.congcongjoa.enums.BooleanStatus;

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
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Entity
@Table(name = "order_detail")
@ToString
public class OrderDetail {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "od_idx", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "or_idx", referencedColumnName = "or_idx")
    private Order order;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sm_idx", referencedColumnName = "sm_idx")
    private StoreMenu storeMenu;

    @Column(name = "od_name",  length = 200)
    private String odName;

    @Column(name = "od_price")
    private Long odPrice;

    //0: 있음 1: 없음
    @Enumerated(EnumType.STRING)
    @Column(name = "od_option", nullable = false)
    private BooleanStatus odOption;
    
    @Column(name = "od_option_price")
    private Long odOptionPrice;

    @Column(name = "od_total")
    private Long odTotal;

    @Column(name = "od_none", length = 200)
    private String odNone;
    
}
