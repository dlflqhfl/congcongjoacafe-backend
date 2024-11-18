package com.congcongjoa.congcongjoa.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Getter
@Setter
public class AddressVo {

    @Column(name = "m_post", length = 200)
    private String postCode;

    @Column(name = "m_street", length = 200)
    private String street;

    @Column(name = "m_detail", length = 200)
    private String detail; 
    
}
