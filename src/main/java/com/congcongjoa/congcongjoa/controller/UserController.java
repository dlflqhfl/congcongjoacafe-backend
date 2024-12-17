package com.congcongjoa.congcongjoa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.congcongjoa.congcongjoa.service.MemberService;

@Controller
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    MemberService ms;

    @RequestMapping("/test")
    public void requestMethodName(@RequestParam("param") String param) {
        System.out.println("param: "+param);
    }

    @RequestMapping("/register/duplicate")
    public ResponseEntity<Boolean> register_duplicate(@RequestParam(required = false) String email) {
        System.out.println("email: "+email);
        boolean isDuplicate = ms.isEmailDuplicate(email);
        return ResponseEntity.ok(isDuplicate);
    }
    
    
}
