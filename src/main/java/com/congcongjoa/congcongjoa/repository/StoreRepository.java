package com.congcongjoa.congcongjoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.congcongjoa.congcongjoa.entity.Store;
import com.congcongjoa.congcongjoa.repository.custom.StoreRepositoryCustom;

public interface StoreRepository extends JpaRepository<Store, Long>, StoreRepositoryCustom {

    Store findBysCode(String sCode);

    Store findBysName(String sName);
    
}
