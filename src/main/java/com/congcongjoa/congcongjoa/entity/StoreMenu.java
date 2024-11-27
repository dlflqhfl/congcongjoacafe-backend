package com.congcongjoa.congcongjoa.entity;

import java.util.ArrayList;
import java.util.List;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "store_menu")
@ToString(exclude = {"orderDetails", "store", "menu"})
public class StoreMenu {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sm_idx", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "s_idx", referencedColumnName = "s_idx")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mn_idx", referencedColumnName = "mn_idx")
    private Menu menu;

    //0: 등록 1: 삭제
    @Enumerated(EnumType.STRING)
    @Column(name = "sm_status", nullable = false)
    private BooleanStatus smStatus;

    //0: 판매 1: 품절
    @Enumerated(EnumType.STRING)
    @Column(name = "sm_sold", nullable = false)
    private BooleanStatus smSold;

    @Column(name = "sm_none", length = 200)
    private String smNone;

    @OneToMany(mappedBy = "storeMenu" , fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetails = new ArrayList<>();
    
}
