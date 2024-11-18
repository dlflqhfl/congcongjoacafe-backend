package com.congcongjoa.congcongjoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.congcongjoa.congcongjoa.entity.Allergy;
import com.congcongjoa.congcongjoa.repository.custom.AllergyRepositoryCustom;

public interface AllergyRepository extends JpaRepository<Allergy, Long>, AllergyRepositoryCustom {
    
}
