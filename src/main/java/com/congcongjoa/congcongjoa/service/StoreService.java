package com.congcongjoa.congcongjoa.service;

import com.congcongjoa.congcongjoa.dto.StoreDTO;
import com.congcongjoa.congcongjoa.dto.custom.CustomOwnerDetails;
import com.congcongjoa.congcongjoa.entity.Store;
import com.congcongjoa.congcongjoa.dto.StoreDTO;
import com.congcongjoa.congcongjoa.dto.custom.CustomOwnerDetails;
import com.congcongjoa.congcongjoa.entity.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.congcongjoa.congcongjoa.dto.custom.RegStoreDTO;
import com.congcongjoa.congcongjoa.entity.Store;
import com.congcongjoa.congcongjoa.enums.BooleanStatus;
import com.congcongjoa.congcongjoa.enums.StoreStatus;
import com.congcongjoa.congcongjoa.repository.StoreRepository;

import java.util.List;

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

    // 모든 매장의 키값과 이름을 가져온다
    public List<StoreDTO> findSIdAndName() {

        return storeRepository.findSName();
    }

    public UserDetails findUserBySNameAndPasswordAndSCode(String sName, String password, String sCode) {
        // 여기에 sName, sCode로 사용자를 조회하고 password를 확인하는 로직을 작성합니다.

        Store store = storeRepository.findAllBySNameAndSCode(sName, sCode);

        if (store.setSStatus();)

        if (!password.equals(store.getSPw())) {
            return null;
        }

        return new CustomOwnerDetails(store);
    }

}
