package com.congcongjoa.congcongjoa.entity;

import java.util.ArrayList;
import java.util.List;

import com.congcongjoa.congcongjoa.entity.enumurate.BooleanStatus;
import com.congcongjoa.congcongjoa.vo.AddressVo;
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
@Table(name = "store")
@ToString(exclude = {"storeMenus", "images"})
public class Store {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "s_idx", nullable = false)
    private Long id;
    
    @Column(name = "s_code", nullable = false, length = 200)
    private String sCode;

    @Column(name = "s_pw", nullable = false, length = 200)
    private String sPw;

    @Column(name = "s_access_token", length = 1024)
    private String sAccessToken;

    @Column(name = "s_refresh_token", length = 1024)
    private String sRefreshToken;
    
    @Column(name = "s_name", nullable = false, length = 200)
    private String sName;
    
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "postCode", column = @Column(name = "s_post", length = 200)),
        @AttributeOverride(name = "street", column = @Column(name = "s_street", length = 200)),
        @AttributeOverride(name = "detail", column = @Column(name = "s_detail", length = 200))
    })
    private AddressVo sAddress;

    @Column(name = "x_axis", length = 100)
    private String xAxis;

    @Column(name = "y_axis", length = 100)
    private String yAxis;

    @Column(name = "region", length = 100)
    private String region;

    @Column(name = "s_phone", length = 100)
    private String sPhone;

    //0: 매장이용 1: 포장만 가능
    @Enumerated(EnumType.STRING)
    @Column(name = "to_go", nullable = false)
    private BooleanStatus toGo;

    @Column(name = "ceo", length = 100)
    private String ceo;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "start", column = @Column(name = "s_start", nullable = false)),
            @AttributeOverride(name = "end", column = @Column(name = "s_end", nullable = false))
    })
    private StartEndVo sStartEnd;

    //0: 주차가능 1: 주차불가
    @Enumerated(EnumType.STRING)
    @Column(name = "s_park", nullable = false)
    private BooleanStatus sPark;

    @Column(name = "directions", length = 200)
    private String directions;

    //0: 운영 1: 폐점
    @Enumerated(EnumType.STRING)
    @Column(name = "s_status", nullable = false)
    private BooleanStatus sStatus;

    @Column(name = "s_none", length = 200)
    private String sNone;

    @OneToMany(mappedBy = "store" , fetch = FetchType.LAZY)
    private List<StoreMenu> storeMenus = new ArrayList<>();

    @OneToMany(mappedBy = "store" , fetch = FetchType.LAZY)
    private List<Image> images = new ArrayList<>();

}   
