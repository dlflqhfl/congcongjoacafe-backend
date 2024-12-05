package com.congcongjoa.congcongjoa.controller;


import com.congcongjoa.congcongjoa.RsData.RsData;
import com.congcongjoa.congcongjoa.enums.ResponseCode;
import com.congcongjoa.congcongjoa.service.StoreService;
import com.congcongjoa.congcongjoa.service.custom.TokenService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/owner")
public class OwnerController {

    @Autowired
    private StoreService storeService;

    @PostMapping("/stores")
    public void setupStore(){
        System.out.println("setupStore");
    }
}