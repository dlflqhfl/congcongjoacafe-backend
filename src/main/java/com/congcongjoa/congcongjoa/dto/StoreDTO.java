package com.congcongjoa.congcongjoa.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import com.congcongjoa.congcongjoa.enums.BooleanStatus;
import com.congcongjoa.congcongjoa.vo.AddressVo;
import com.congcongjoa.congcongjoa.vo.StartEndVo;

@Data
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
    private String region;
    private String sPhone;
    private BooleanStatus toGo;
    private String ceo;
    private StartEndVo sStartEnd;
    private BooleanStatus sPark;
    private String directions;
    private BooleanStatus sStatus;
    private String sNone;
    private List<StoreMenuDTO> storeMenus = new ArrayList<>();
    private List<ImageDTO> images = new ArrayList<>();

}
