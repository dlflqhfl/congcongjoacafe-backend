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
import lombok.*;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "allergy")
@ToString(exclude = {"menu"})
public class Allergy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "a_idx", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mn_idx", referencedColumnName = "mn_idx")
    private Menu menu;
    
    //0: 있음 1: 없음
    @Enumerated(EnumType.STRING)
    @Column(name = "a_soy", nullable = false)
    private BooleanStatus aSoy;

    //0: 있음 1: 없음
    @Enumerated(EnumType.STRING)
    @Column(name = "a_milk", nullable = false)
    private BooleanStatus aMilk;

    //0: 있음 1: 없음
    @Enumerated(EnumType.STRING)
    @Column(name = "a_egg", nullable = false)
    private BooleanStatus aEgg;

    //0: 있음 1: 없음
    @Enumerated(EnumType.STRING)
    @Column(name = "a_wheat", nullable = false)
    private BooleanStatus aWheat;

    @Column(name = "a_none", length = 200)
    private String aNone;
    
}
