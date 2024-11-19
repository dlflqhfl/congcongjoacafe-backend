package com.congcongjoa.congcongjoa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.congcongjoa.congcongjoa.repository.MenuOptionRepository;

@Service
public class MenuOptionService {

    @Autowired
    private MenuOptionRepository menuOptionRepository;
    
}
