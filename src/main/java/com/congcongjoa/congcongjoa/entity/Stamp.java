package com.congcongjoa.congcongjoa.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "stamp")
@ToString(exclude = {"member"})
public class Stamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "st_idx", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "m_idx", referencedColumnName = "m_idx")
    private Member member;

    @Column(name = "st_save")
    private Long stSave;

    @Column(name = "st_minus")
    private Long stMinus;

    @Column(name = "st_total")
    private Long stTotal;

    @Column(name = "st_expire")
    private LocalDateTime stExpire;
    
    @Column(name = "st_date")
    private LocalDateTime stDate;
    
    @Column(name = "st_none", length = 200)
    private String stNone;

}
