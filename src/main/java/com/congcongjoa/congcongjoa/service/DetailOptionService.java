package com.congcongjoa.congcongjoa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.congcongjoa.congcongjoa.repository.DetailOptionRepository;

@Service
public class DetailOptionService {

    @Autowired
    private DetailOptionRepository detailOptionRepository;
    
}
