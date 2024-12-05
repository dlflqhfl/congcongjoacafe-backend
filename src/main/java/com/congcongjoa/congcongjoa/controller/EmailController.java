package com.congcongjoa.congcongjoa.controller;

import java.security.NoSuchAlgorithmException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.congcongjoa.congcongjoa.dto.EmailRequestDto;
import com.congcongjoa.congcongjoa.service.EmailService;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

@RestController  
@RequiredArgsConstructor  
@RequestMapping("/email")  
public class EmailController {
    private final EmailService emailService;  
  
    @GetMapping("/{email_addr}/authcode")  
    public ResponseEntity<String> sendEmailPath(@PathVariable String email_addr) throws MessagingException {  
        emailService.sendEmail(email_addr);  
        return ResponseEntity.ok("이메일을 확인하세요");  
    }  
  
    @PostMapping("/{email_addr}/authcode")  
    public ResponseEntity<String> sendEmailAndCode(@PathVariable String email_addr, @RequestBody EmailRequestDto dto) throws NoSuchAlgorithmException {  
        if (emailService.verifyEmailCode(email_addr, dto.getCode())) {  
            return ResponseEntity.ok(emailService.makeMemberId(email_addr));  
        }  
        return ResponseEntity.notFound().build();  
    }  
}
