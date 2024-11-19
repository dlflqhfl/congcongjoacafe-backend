package com.congcongjoa.congcongjoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.congcongjoa.congcongjoa.entity.Grade;
import com.congcongjoa.congcongjoa.repository.custom.GradeRepositoryCustom;

public interface  GradeRepository extends JpaRepository<Grade, Long>, GradeRepositoryCustom {
    
}
