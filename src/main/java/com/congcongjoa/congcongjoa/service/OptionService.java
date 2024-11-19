package com.congcongjoa.congcongjoa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.congcongjoa.congcongjoa.repository.OptionRepository;

@Service
public class OptionService {

    @Autowired
    private OptionRepository optionRepository;
    
}
