package com.congcongjoa.congcongjoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.congcongjoa.congcongjoa.entity.Menu;
import com.congcongjoa.congcongjoa.repository.custom.MenuRepositoryCustom;

public interface MenuRepository extends JpaRepository<Menu, Long>, MenuRepositoryCustom {
    
}
