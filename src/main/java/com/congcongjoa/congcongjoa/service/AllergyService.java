package com.congcongjoa.congcongjoa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.congcongjoa.congcongjoa.repository.AllergyRepository;

@Service
public class AllergyService {
    
    @Autowired
    private AllergyRepository allergyRepository;
    
}
