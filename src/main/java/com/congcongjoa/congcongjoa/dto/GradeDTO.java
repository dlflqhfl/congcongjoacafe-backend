package com.congcongjoa.congcongjoa.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class GradeDTO {

    private Long id;
    private String gName;
    private Long gReq;
    private String gNone;
    private List<MemberDTO> members = new ArrayList<>();

}
