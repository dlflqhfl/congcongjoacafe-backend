package com.congcongjoa.congcongjoa.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class OptionDTO {

    private Long id;
    private String opName;
    private Long opPrice;
    private String opNone;
    private List<MenuOptionDTO> menuOptions = new ArrayList<>();

}
