package com.congcongjoa.congcongjoa.dto;

import lombok.Data;

import com.congcongjoa.congcongjoa.entity.enumurate.BooleanStatus;

@Data
public class ImageDTO {

    private Long id;
    private Long sIdx;
    private Long mnIdx;
    private BooleanStatus iCate;
    private String iName;
    private BooleanStatus iStatus;
    private String iNone;

}
