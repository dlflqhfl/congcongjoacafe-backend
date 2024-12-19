package com.congcongjoa.congcongjoa.service;

import com.congcongjoa.congcongjoa.dto.StoreDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.congcongjoa.congcongjoa.repository.StoreMenuRepository;

import java.util.List;

@Service
public class StoreMenuService {
    
    @Autowired
    private StoreMenuRepository storeMenuRepository;

    public List<StoreDTO> getStoreList(String sName) {

        return null;
    }
}
