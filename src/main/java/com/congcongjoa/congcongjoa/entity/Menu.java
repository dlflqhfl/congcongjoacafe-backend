package com.congcongjoa.congcongjoa.entity;

import java.util.ArrayList;
import java.util.List;

import com.congcongjoa.congcongjoa.enums.BooleanStatus;
import com.congcongjoa.congcongjoa.enums.MenuCate;
import com.congcongjoa.congcongjoa.enums.Size;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Entity
@Table(name = "menu")
@ToString(exclude = {"allergy", "nutrition", "menuOption", "storeMenu", "images"})
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mn_idx", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "mn_cate", nullable = false)
    private MenuCate mnCate;

    @Column(name = "mn_name", nullable = false, length = 200)
    private String mnName;

    @Column(name = "mn_detail", length = 200)
    private String mnDetail;

    @Enumerated(EnumType.STRING)
    @Column(name = "mn_size", nullable = false)
    private Size mnSize;

    @Column(name = "mn_price", nullable = false)
    private Long mnPrice;

    //0: 판매 1: 판매종료
    @Enumerated(EnumType.STRING)
    @Column(name = "mn_status", nullable = false)
    private BooleanStatus mnStatus;

    @Column(name = "mn_none", length = 200)
    private String mnNone;

    @OneToOne(mappedBy = "menu" , fetch = FetchType.LAZY)
    private Allergy allergy;

    @OneToOne(mappedBy = "menu" , fetch = FetchType.LAZY)
    private Nutrition nutrition;

    @Builder.Default
    @OneToMany(mappedBy = "menu" , fetch = FetchType.LAZY)
    private List<MenuOption> menuOption  = new ArrayList<>();

    @OneToOne(mappedBy = "menu" , fetch = FetchType.LAZY)
    private StoreMenu storeMenu;

    @Builder.Default
    @OneToMany(mappedBy = "menu" , fetch = FetchType.LAZY)
    private List<Image> images = new ArrayList<>();
    
}
