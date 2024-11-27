package com.congcongjoa.congcongjoa.entity;

import java.util.ArrayList;
import java.util.List;

import com.congcongjoa.congcongjoa.enums.BooleanStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Entity
@Table(name = "options")
@ToString(exclude = {"menuOptions"})
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "op_idx", nullable = false)
    private Long id;
    
    @Column(name = "op_name", nullable = false, length = 200)
    private String opName;

    @Column(name = "op_price")
    private Long opPrice;

    @Column(name = "op_status")
    private BooleanStatus opStatus;

    @Column(name = "op_none", length = 200)
    private String opNone;

    @Builder.Default
    @OneToMany(mappedBy = "option" , fetch = FetchType.LAZY)
    private List<MenuOption> menuOptions = new ArrayList<>();
      
}
