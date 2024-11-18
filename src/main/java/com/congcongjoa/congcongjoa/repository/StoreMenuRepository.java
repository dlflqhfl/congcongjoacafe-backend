package com.congcongjoa.congcongjoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.congcongjoa.congcongjoa.entity.StoreMenu;
import com.congcongjoa.congcongjoa.repository.custom.StoreMenuRepositoryCustom;

public interface StoreMenuRepository extends JpaRepository<StoreMenu, Long>, StoreMenuRepositoryCustom {
    
}
