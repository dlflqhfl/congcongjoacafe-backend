package com.congcongjoa.congcongjoa.dto.custom;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerLoginDTO {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("sCode")
    private String sCode;

    @JsonProperty("password")
    private String password;

    @JsonProperty("sName")
    private String sName;
}