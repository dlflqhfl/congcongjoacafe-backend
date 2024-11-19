package com.congcongjoa.congcongjoa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/test")
    public void requestMethodName(@RequestParam("param") String param) {
        System.out.println("param: "+param);
    }
    
}
