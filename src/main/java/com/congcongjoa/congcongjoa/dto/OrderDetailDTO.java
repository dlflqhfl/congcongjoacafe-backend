package com.congcongjoa.congcongjoa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.congcongjoa.congcongjoa.entity.enumurate.BooleanStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {

    private Long id;
    private Long orIdx;
    private Long smIdx;
    private String odName;
    private Long odPrice;
    private BooleanStatus odOption;
    private Long odOptionPrice;
    private Long odTotal;
    private String odNone;

}
