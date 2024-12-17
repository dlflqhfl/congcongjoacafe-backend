package com.congcongjoa.congcongjoa.service.custom;

import com.congcongjoa.congcongjoa.dto.custom.CustomOwnerDetails;
import com.congcongjoa.congcongjoa.entity.Store;
import com.congcongjoa.congcongjoa.enums.StoreStatus;
import com.congcongjoa.congcongjoa.repository.StoreRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    //owner jwt로그인 인증
    public UserDetails findUserBySNameAndPasswordAndSCode(String sName, String password, String sCode) {
        Store store = storeRepository.findBySNameAndSCode(sName, sCode);

        if (store == null || !isPasswordValid(store, password)) {
            return null;
        }

        return new CustomOwnerDetails(store.getSCode(), store.getSPw());
    }

    private boolean isPasswordValid(Store store, String password) {
        StoreStatus status = store.getSStatus();

        return switch (status) {
            case REGISTERED -> store.getSPw().equals(password);
            case OPEN -> passwordEncoder.matches(password, store.getSPw());
            default -> false;
        };
    }

}
