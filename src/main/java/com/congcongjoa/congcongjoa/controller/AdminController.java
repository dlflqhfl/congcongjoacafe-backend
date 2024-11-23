package com.congcongjoa.congcongjoa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.congcongjoa.congcongjoa.RsData.RsData;
import com.congcongjoa.congcongjoa.dto.custom.RegStoreDTO;
import com.congcongjoa.congcongjoa.enums.ResponseCode;
import com.congcongjoa.congcongjoa.service.StoreService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private StoreService storeService;

    @GetMapping("/checkStoreName")
    public RsData<String> checkStoreName(@RequestParam String storeName) {

        System.out.println("storeCode:"+storeName);
        
        boolean result = storeService.checkStoreName(storeName);

        System.out.println("result:"+result);

        if (result) {
            System.out.println("성공");
            return ResponseCode.OK.toRsData(null);
        } else {
            return ResponseCode.USER_ALREADY_EXIST.toRsData(null);
        }
        
    }

    @GetMapping("/checkStoreCode")
    public RsData<String> checkStoreCode(@RequestParam String storeCode) {

        System.out.println("storeCode:"+storeCode);
        
        boolean result = storeService.checkStoreCode(storeCode);

        System.out.println("result:"+result);

        if (result) {
            System.out.println("성공");
            return ResponseCode.OK.toRsData(null);
        } else {
            return ResponseCode.USER_ALREADY_EXIST.toRsData(null);
        }
        
    }

    @PostMapping("/regStore")
    public RsData<String> regStore(@Valid @RequestBody RegStoreDTO regStoreDTO) {
        
        boolean result = storeService.regStore(regStoreDTO);

        if (result) {
            return ResponseCode.OK.toRsData(null);
        } else {
            return ResponseCode.USER_ALREADY_EXIST.toRsData(null);
        }
    }
    
}
