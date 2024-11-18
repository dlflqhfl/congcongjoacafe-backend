package com.congcongjoa.congcongjoa.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StartEndVo {

    @Column(name = "s_start")
    private LocalDateTime start;

    @Column(name = "s_end")
    private LocalDateTime end;

}
