package com.congcongjoa.congcongjoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.congcongjoa.congcongjoa.entity.DetailOption;
import com.congcongjoa.congcongjoa.repository.custom.DetailOptionRepositoryCustom;

public interface DetailOptionRepository extends JpaRepository<DetailOption, Long>, DetailOptionRepositoryCustom {
    
}
