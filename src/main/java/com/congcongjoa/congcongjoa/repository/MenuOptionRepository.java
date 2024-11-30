package com.congcongjoa.congcongjoa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.congcongjoa.congcongjoa.entity.MenuOption;
import com.congcongjoa.congcongjoa.repository.custom.MenuOptionRepositoryCustom;

public interface MenuOptionRepository extends JpaRepository<MenuOption, Long>, MenuOptionRepositoryCustom {

    List<MenuOption> findByMenuId(Long mnId);
    
}
