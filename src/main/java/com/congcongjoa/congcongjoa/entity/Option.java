package com.congcongjoa.congcongjoa.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "option")
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

    @Column(name = "op_none", length = 200)
    private String opNone;

    @OneToMany(mappedBy = "option" , fetch = FetchType.LAZY)
    private List<MenuOption> menuOptions = new ArrayList<>();
      
}
