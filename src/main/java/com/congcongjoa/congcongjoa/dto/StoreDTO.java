package com.congcongjoa.congcongjoa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class StoreDTO {

    private Long id;
    private String sCode;
    private String sPw;
    @JsonProperty("s_name")
    private String sName;
    @JsonProperty("s_address")
    private AddressVo sAddress;
    @JsonProperty("x_axis")
    private String xAxis;
    @JsonProperty("y_axis")
    private String yAxis;
    @JsonProperty("s_phone")
    private String sPhone;
    private String ceo;
    private StartEndVo sStartEnd;
    @JsonProperty("s_drive_thru")
    private BooleanStatus sDriveThru;
    @JsonProperty("s_park")
    private BooleanStatus sPark;
    @JsonProperty("s_store_use")
    private BooleanStatus sStoreUse;
    @JsonProperty("s_wifi")
    private BooleanStatus sWifi;
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
