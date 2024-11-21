package com.congcongjoa.congcongjoa.repository;

import com.congcongjoa.congcongjoa.entity.Store;
import com.congcongjoa.congcongjoa.repository.custom.StoreRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long>, StoreRepositoryCustom {
    @Query("SELECT s FROM Store s WHERE s.sName = :sName AND s.sCode = :sCode")
    Store findAllBySNameAndSCode(@Param("sName") String sName, @Param("sCode") String sCode);


    Store findBysCode(String sCode);

    Store findBysName(String sName);
}