package com.congcongjoa.congcongjoa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.congcongjoa.congcongjoa.repository.StampRepository;

@Service
public class StampService {
    
    @Autowired
    private StampRepository stampRepository;
    
}
