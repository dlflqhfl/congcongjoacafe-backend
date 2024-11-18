package com.congcongjoa.congcongjoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.congcongjoa.congcongjoa.entity.Option;
import com.congcongjoa.congcongjoa.repository.custom.OptionRepositoryCustom;

public interface OptionRepository extends JpaRepository<Option, Long>, OptionRepositoryCustom {
    
}
