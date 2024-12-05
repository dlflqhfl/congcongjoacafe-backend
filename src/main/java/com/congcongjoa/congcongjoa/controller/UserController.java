package com.congcongjoa.congcongjoa.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.congcongjoa.congcongjoa.dto.MemberDTO;
import com.congcongjoa.congcongjoa.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor  
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    MemberService ms;

    @GetMapping("/register/duplicate/{email}")
    public ResponseEntity<Boolean> register_duplicate(@PathVariable("email") String email) {
        System.out.println("/register/duplicate param email: "+email);
        boolean isDuplicate = ms.isEmailDuplicate(email);
        System.out.println("isDuplicate = "+isDuplicate);
        return ResponseEntity.ok(isDuplicate);
    }
    
    @PostMapping("/register")
    public ResponseEntity<String> requestMethodName(@RequestBody Map<String, Object> payload) {
        System.out.println("회원가입 중");

       // Manual mapping for debugging
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMName((String) payload.get("mName"));
        memberDTO.setMEmail((String) payload.get("mEmail"));
        memberDTO.setMPw((String) payload.get("mPw"));
        memberDTO.setMPhone((String) payload.get("mPhone"));

        boolean isComplete = ms.registerMember(memberDTO);

        //if(isComplete)
            return ResponseEntity.ok("회원가입 완료");  
        

    }
    
    
}
