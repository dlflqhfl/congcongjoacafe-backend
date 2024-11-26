package com.congcongjoa.congcongjoa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Table(name = "nutrition")
@ToString
public class Nutrition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "n_idx", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mn_idx", referencedColumnName = "mn_idx")
    private Menu menu;

    @Column(name = "n_one")
    private Long nOne;

    @Column(name = "n_cal")
    private Long nCal;

    @Column(name = "n_caffeine")
    private Long nCaffeine;

    @Column(name = "n_carbo")
    private Long nCarbo;

    @Column(name = "n_sugar")
    private Long nSugar;

    @Column(name = "n_salt")
    private Long nSalt;

    @Column(name = "n_protein")
    private Long nProtein;

    @Column(name = "n_fat")
    private Long nFat;

    @Column(name = "n_none", length = 200)
    private String nNone;
    
}
