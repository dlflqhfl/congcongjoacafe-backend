package com.congcongjoa.congcongjoa.entity;

import java.util.ArrayList;
import java.util.List;

import com.congcongjoa.congcongjoa.enums.BooleanStatus;
import com.congcongjoa.congcongjoa.enums.StoreStatus;
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
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@Getter
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

    @Column(name = "s_phone", length = 100)
    private String sPhone;

    @Column(name = "ceo", length = 100)
    private String ceo;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "start", column = @Column(name = "s_start")),
            @AttributeOverride(name = "end", column = @Column(name = "s_end"))
    })
    private StartEndVo sStartEnd;

    @Enumerated(EnumType.STRING)
    @Column(name = "s_drive_thru")
    private BooleanStatus sDriveThru;

    //0: 주차가능 1: 주차불가
    @Enumerated(EnumType.STRING)
    @Column(name = "s_park")
    private BooleanStatus sPark;

    //0: 매장이용 1: 포장만 가능
    @Enumerated(EnumType.STRING)
    @Column(name = "s_store_use")
    private BooleanStatus sStoreUse;

    @Enumerated(EnumType.STRING)
    @Column(name = "s_wifi")
    private BooleanStatus sWifi;

    @Column(name = "s_directions", length = 1000)
    private String sDirections;

    //0: 둥록요청 1: 운영중 2:폐점
    @Enumerated(EnumType.STRING)
    @Column(name = "s_status")
    private StoreStatus sStatus;

    @Column(name = "s_none", length = 200)
    private String sNone;

    @Builder.Default
    @OneToMany(mappedBy = "store" , fetch = FetchType.LAZY)
    private List<StoreMenu> storeMenus = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "store" , fetch = FetchType.LAZY)
    private List<Image> images = new ArrayList<>();

}   
