package com.congcongjoa.congcongjoa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.congcongjoa.congcongjoa.entity.Image;
import com.congcongjoa.congcongjoa.entity.Menu;
import com.congcongjoa.congcongjoa.repository.custom.ImageRepositoryCustom;

public interface ImageRepository extends JpaRepository<Image, Long>, ImageRepositoryCustom {

    List<Image> findByMenu(Menu menu);

    Image findByiNameAndMenu(String iName, Menu menu);
    
}
