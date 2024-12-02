package com.congcongjoa.congcongjoa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class NutritionDTO {

    private Long id;
    private Long mnIdx;

    @JsonProperty("nOne")
    private Long nOne;

    @JsonProperty("nCal")
    private Long nCal;

    @JsonProperty("nCaffeine")
    private Long nCaffeine;

    @JsonProperty("nCarbo")
    private Long nCarbo;

    @JsonProperty("nSugar")
    private Long nSugar;

    @JsonProperty("nSalt")
    private Long nSalt;

    @JsonProperty("nProtein")
    private Long nProtein;

    @JsonProperty("nFat")
    private Long nFat;
    private String nNone;

}
