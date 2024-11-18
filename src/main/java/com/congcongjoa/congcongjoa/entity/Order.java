package com.congcongjoa.congcongjoa.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.congcongjoa.congcongjoa.entity.enumurate.OrderStatus;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "order")
@ToString(exclude = {"orderDetails", "payments"})
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "or_idx", nullable = false)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "m_idx", referencedColumnName = "m_idx")
    private Member member;

    @Enumerated(EnumType.STRING)
    @Column(name = "or_status")
    private OrderStatus orStatus;

    @Column(name = "or_date")
    private LocalDateTime orDate;
    
    @Column(name = "or_none", length = 200)
    private String orNone;

    @OneToMany(mappedBy = "order" , fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetails = new ArrayList<>();

    @OneToMany(mappedBy = "order" , fetch = FetchType.LAZY)
    private List<Payment> payments = new ArrayList<>();

}