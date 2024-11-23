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

    @Transactional
    public void saveOwnerToken(String sCode, String accessToken, String refreshToken, Authentication authentication) {
        Store store = storeRepository.findBysCode(sCode);

        // 로그 출력 추가
        System.out.println("Store 상태: " + store.getSStatus());

        // Store 상태를 확인하여 isFirstLogin 값 설정
        boolean isFirstLogin = store.getSStatus() == StoreStatus.REGISTERED;

        // 로그 출력 추가
        System.out.println("isFirstLogin 값: " + isFirstLogin);

        store.updateTokens(accessToken, refreshToken);
        storeRepository.save(store);

        // CustomOwnerDetails에 isFirstLogin 값 설정
        CustomOwnerDetails customOwnerDetails = (CustomOwnerDetails) authentication.getPrincipal();
        customOwnerDetails.setFirstLogin(isFirstLogin);

        // 로그 출력 추가
        System.out.println("CustomOwnerDetails isFirstLogin 값: " + customOwnerDetails.isFirstLogin());
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
