package com.congcongjoa.congcongjoa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.congcongjoa.congcongjoa.repository.NutritionRepository;

@Service
public class NutritionService {

    @Autowired
    private NutritionRepository nutritionRepository;
    
}
