package com.congcongjoa.congcongjoa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.congcongjoa.congcongjoa.repository.StoreMenuRepository;

@Service
public class StoreMenuService {
    
    @Autowired
    private StoreMenuRepository storeMenuRepository;
    
}
