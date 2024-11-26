package com.congcongjoa.congcongjoa.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Entity
@Table(name = "menu_option")
@ToString(exclude = {"detailOptions", "menu", "option"})
public class MenuOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mo_idx", nullable = false)
    private Long id;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mn_idx", referencedColumnName = "mn_idx")
    private Menu menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "op_idx", referencedColumnName = "op_idx")
    private Option option;

    @Column(name = "mo_none", length = 200)
    private String moNone;

    @Builder.Default
    @OneToMany(mappedBy = "menuOption" , fetch = FetchType.LAZY)
    private List<DetailOption> detailOptions = new ArrayList<>();

}
