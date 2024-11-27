package com.congcongjoa.congcongjoa.entity;

import com.congcongjoa.congcongjoa.enums.BooleanStatus;

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
@Table(name = "image")
@ToString(exclude = {"store", "menu"})
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "i_idx", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "s_idx", referencedColumnName = "s_idx")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mn_idx", referencedColumnName = "mn_idx")
    private Menu menu;

    //0 매장 / 1 메뉴
    @Column(name = "i_cate", nullable = false)
    private BooleanStatus iCate;

    @Column(name = "i_name", nullable = false, length = 200)
    private String iName;

    //0 메인 1 노메인
    @Column(name = "i_main", nullable = false)
    private BooleanStatus iMain;

    //0 등록 / 1 삭제
    @Column(name = "i_status", nullable = false)
    private BooleanStatus iStatus;

    @Column(name = "i_none", length = 200)
    private String iNone;
    
}
