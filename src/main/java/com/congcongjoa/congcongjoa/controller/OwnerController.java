package com.congcongjoa.congcongjoa.controller;


import com.congcongjoa.congcongjoa.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/owner")
public class OwnerController {

    @Autowired
    private StoreService storeService;


}