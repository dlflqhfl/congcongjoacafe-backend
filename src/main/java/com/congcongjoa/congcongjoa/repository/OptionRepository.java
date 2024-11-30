package com.congcongjoa.congcongjoa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.congcongjoa.congcongjoa.entity.Option;
import com.congcongjoa.congcongjoa.repository.custom.OptionRepositoryCustom;

public interface OptionRepository extends JpaRepository<Option, Long>, OptionRepositoryCustom {

    @Query("SELECT o FROM Option o WHERE o.opStatus =0")
    List<Option> findOptionAll();
    
}
