package com.congcongjoa.congcongjoa.dto;

import com.congcongjoa.congcongjoa.entity.enumurate.BooleanStatus;

import lombok.Data;

@Data
public class AllergyDTO {

    private Long id;
    private Long mnIdx;
    private BooleanStatus aSoy;
    private BooleanStatus aMilk;
    private BooleanStatus aEgg;
    private BooleanStatus aWheat;
    private String aNone;

}
