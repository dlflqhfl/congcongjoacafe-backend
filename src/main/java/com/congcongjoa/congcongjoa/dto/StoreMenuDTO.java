package com.congcongjoa.congcongjoa.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import com.congcongjoa.congcongjoa.entity.enumurate.BooleanStatus;

@Data
public class StoreMenuDTO {

    private Long id;
    private Long sIdx;
    private Long mnIdx;
    private BooleanStatus smStatus;
    private BooleanStatus smSold;
    private String smNone;
    private List<OrderDetailDTO> orderDetails = new ArrayList<>();

}
