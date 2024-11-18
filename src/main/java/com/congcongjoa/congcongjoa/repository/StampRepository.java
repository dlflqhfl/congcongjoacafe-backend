package com.congcongjoa.congcongjoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.congcongjoa.congcongjoa.entity.Stamp;
import com.congcongjoa.congcongjoa.repository.custom.StampRepositoryCustom;

public interface StampRepository extends JpaRepository<Stamp, Long>, StampRepositoryCustom {
    
}
