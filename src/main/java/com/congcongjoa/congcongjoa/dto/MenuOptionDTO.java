package com.congcongjoa.congcongjoa.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class MenuOptionDTO {

    private Long id;
    private Long mnIdx;
    private Long opIdx;
    private String moNone;
    private List<DetailOptionDTO> detailOptions = new ArrayList<>();

}
