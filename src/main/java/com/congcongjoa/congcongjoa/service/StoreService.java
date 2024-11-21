package com.congcongjoa.congcongjoa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.congcongjoa.congcongjoa.dto.custom.RegStoreDTO;
import com.congcongjoa.congcongjoa.entity.Store;
import com.congcongjoa.congcongjoa.enums.BooleanStatus;
import com.congcongjoa.congcongjoa.enums.StoreStatus;
import com.congcongjoa.congcongjoa.repository.StoreRepository;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    public boolean checkStoreName(String storeName) {
        try {
            Store store = storeRepository.findBysName(storeName);
            return store == null;
        } catch (Exception e) {
            System.err.println("Error checking store code: " + e.getMessage());
            return false;
        }
    }

    public boolean checkStoreCode(String storeCode) {
        try {
            Store store = storeRepository.findBysCode(storeCode);
            return store == null;
        } catch (Exception e) {
            System.err.println("Error checking store code: " + e.getMessage());
            return false;
        }
    }

    public boolean regStore(RegStoreDTO regStoreDTO){
        try {

            Store store = new Store();
            
            store.setSCode(regStoreDTO.getStoreCode());
            store.setSName(regStoreDTO.getName());
            store.setSPw(regStoreDTO.getInitialPassword());
            store.setSStatus(StoreStatus.REGISTERED);
    
            storeRepository.save(store);

            return true;

        } catch (Exception e) {

            return false;

        }

    }
    
}
