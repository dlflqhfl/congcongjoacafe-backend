package com.congcongjoa.congcongjoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.congcongjoa.congcongjoa.entity.Menu;
import com.congcongjoa.congcongjoa.entity.Nutrition;
import com.congcongjoa.congcongjoa.repository.custom.NutritionRepositoryCustom;

public interface NutritionRepository extends JpaRepository<Nutrition, Long>, NutritionRepositoryCustom {

    Nutrition findByMenu(Menu menu);
    
}
