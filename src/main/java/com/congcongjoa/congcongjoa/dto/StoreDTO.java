package com.congcongjoa.congcongjoa.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import com.congcongjoa.congcongjoa.enums.BooleanStatus;
import com.congcongjoa.congcongjoa.enums.StoreStatus;
import com.congcongjoa.congcongjoa.vo.AddressVo;
import com.congcongjoa.congcongjoa.vo.StartEndVo;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StoreDTO {

    private Long id;
    private String sCode;
    private String sPw;
    private String sAccessToken;
    private String sRefreshToken;
    private String sName;
    private AddressVo sAddress;
    private String xAxis;
    private String yAxis;
    private String sPhone;
    private BooleanStatus toGo;
    private String ceo;
    private StartEndVo sStartEnd;
    private BooleanStatus sPark;
    private String directions;
    private StoreStatus sStatus;
    private String sNone;
    private List<StoreMenuDTO> storeMenus = new ArrayList<>();
    private List<ImageDTO> images = new ArrayList<>();

    @QueryProjection
    public StoreDTO(String sName) {
        this.sName = sName;
    }
}
