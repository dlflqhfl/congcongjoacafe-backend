package com.congcongjoa.congcongjoa.dto;

import lombok.Data;

import com.congcongjoa.congcongjoa.enums.BooleanStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class ImageDTO {

    private Long id;
    private Long sIdx;
    private Long mnIdx;
    private BooleanStatus iCate;
    @JsonProperty("iName")
    private String iName;
    @JsonProperty("iMain")
    private BooleanStatus iMain;
    private BooleanStatus iStatus;
    private String iNone;

}
