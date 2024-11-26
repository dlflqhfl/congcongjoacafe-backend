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
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Entity
@Table(name = "grade")
@ToString(exclude = {"members"})
public class Grade {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "g_idx", nullable = false)
    private Long id;

    @Column(name = "g_name", nullable = false, length = 200)
    private String gName;

    @Column(name = "g_req", nullable = false)
    private Long gReq;

    @Column(name = "g_none", length = 200)
    private String gNone;

    @Builder.Default
    @OneToMany(mappedBy = "grade" , fetch = FetchType.LAZY)
    private List<Member> members = new ArrayList<>();
    
}
