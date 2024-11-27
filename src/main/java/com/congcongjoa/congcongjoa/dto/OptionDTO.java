package com.congcongjoa.congcongjoa.dto;

import java.util.ArrayList;
import java.util.List;

import com.congcongjoa.congcongjoa.enums.BooleanStatus;

import lombok.Data;

@Data
public class OptionDTO {

    private Long id;
    private String opName;
    private Long opPrice;
    private BooleanStatus opStatus;
    private String opNone;
    private List<MenuOptionDTO> menuOptions = new ArrayList<>();

}
