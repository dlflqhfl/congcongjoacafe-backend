package com.congcongjoa.congcongjoa.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StampDTO {
   
    private Long id;
    private Long mIdx;
    private Long stSave;
    private Long stMinus;
    private Long stTotal;
    private LocalDateTime stExpire;
    private LocalDateTime stDate;
    private String stNone;

}
