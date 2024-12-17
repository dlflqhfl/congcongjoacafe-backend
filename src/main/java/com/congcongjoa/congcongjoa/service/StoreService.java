package com.congcongjoa.congcongjoa.service;

import com.congcongjoa.congcongjoa.dto.StoreDTO;
import com.congcongjoa.congcongjoa.entity.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.congcongjoa.congcongjoa.dto.custom.RegStoreDTO;
import com.congcongjoa.congcongjoa.enums.StoreStatus;
import com.congcongjoa.congcongjoa.mapper.StoreMapper;
import com.congcongjoa.congcongjoa.repository.StoreRepository;

import java.util.List;


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

    public boolean regStore(RegStoreDTO regStoreDTO) {
        try {
            
            Store store = Store.builder()
                .sCode(regStoreDTO.getStoreCode())
                .sName(regStoreDTO.getName())
                .sPw(regStoreDTO.getInitialPassword())
                .sStatus(StoreStatus.REGISTERED)
                .build();
    
            storeRepository.save(store);

            return true;

        } catch (Exception e) {

            return false;

        }

    }

    public List<StoreDTO> getAllStore() {
        return StoreMapper.INSTANCE.toStoreDTOList(storeRepository.findAll());
    }

    // 모든 매장의 키값과 이름을 가져온다
    public List<StoreDTO> findIdAndSName() {

        return storeRepository.findSName();
    }


}
