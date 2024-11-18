package com.congcongjoa.congcongjoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.congcongjoa.congcongjoa.entity.Image;
import com.congcongjoa.congcongjoa.repository.custom.ImageRepositoryCustom;

public interface ImageRepository extends JpaRepository<Image, Long>, ImageRepositoryCustom {
    
}
