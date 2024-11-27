package com.congcongjoa.congcongjoa.dto;

import com.congcongjoa.congcongjoa.enums.BooleanStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AllergyDTO {

    private Long id;
    
    private Long mnIdx;

    @JsonProperty("aSoy")
    private BooleanStatus aSoy;

    @JsonProperty("aMilk")
    private BooleanStatus aMilk;

    @JsonProperty("aEgg")
    private BooleanStatus aEgg;

    @JsonProperty("aWheat")
    private BooleanStatus aWheat;

    private String aNone;

}
