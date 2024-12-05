package com.congcongjoa.congcongjoa.service;

import lombok.RequiredArgsConstructor;  
import org.springframework.beans.factory.annotation.Value;  
import org.springframework.mail.javamail.JavaMailSender;  
import org.springframework.stereotype.Service;  
import org.thymeleaf.TemplateEngine;  
import org.thymeleaf.context.Context;  
import org.thymeleaf.templatemode.TemplateMode;  
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import com.congcongjoa.congcongjoa.util.RedisUtil;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.security.MessageDigest;  
import java.security.NoSuchAlgorithmException;  
import java.time.LocalDateTime;  
import java.util.Arrays;  
import java.util.Random;  

@Service  
@RequiredArgsConstructor  
public class EmailService {
    private final JavaMailSender mailSender;  
    private final RedisUtil redisUtil;  
  
    @Value("${spring.mail.username}")  
    private String configEmail;  
  
    private String createdCode() {  
        int leftLimit = 48; // number '0'  
        int rightLimit = 122; // alphabet 'z'  
        int targetStringLength = 6;  
        Random random = new Random();  
  
        return random.ints(leftLimit, rightLimit + 1)  
                .filter(i -> (i <=57 || i >=65) && (i <= 90 || i>= 97))  
                .limit(targetStringLength)  
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)  
                .toString();  
    }  
  
    private String setContext(String code) {  
        Context context = new Context();  
        TemplateEngine templateEngine = new TemplateEngine();  
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();  
  
        context.setVariable("code", code);  
  
  
        templateResolver.setPrefix("templates/");  
        templateResolver.setSuffix(".html");  
        templateResolver.setTemplateMode(TemplateMode.HTML);  
        templateResolver.setCacheable(false);  
  
        templateEngine.setTemplateResolver(templateResolver);  
  
        return templateEngine.process("mail", context);  
    }  
  
  
    // 메일 반환  
  
    private MimeMessage createEmailForm(String email) throws MessagingException { 
        String authCode = createdCode();  
  
        MimeMessage message = mailSender.createMimeMessage();  
        message.addRecipients(MimeMessage.RecipientType.TO, email);  
        message.setSubject("안녕하세요 인증번호입니다.");  
        message.setFrom(configEmail);  
         // 본문 내용 설정 (HTML 형식 및 UTF-8 인코딩)
        String content = setContext(authCode);
        message.setContent(content, "text/html; charset=UTF-8");

        // 인증 코드 Redis에 저장 (30분 유효)
        redisUtil.setDataExpire(email, authCode, 60 * 30L);
        
        return message;
    }  
  
  
    // 메일 보내기  
    public void sendEmail(String toEmail) throws MessagingException {  
        if (redisUtil.existData(toEmail)) {  
            redisUtil.deleteData(toEmail);  
        }  
  
        MimeMessage emailForm = createEmailForm(toEmail);  
        
        mailSender.send(emailForm);  
    }  
  
    // 코드 검증  
    public Boolean verifyEmailCode(String email, String code) {  
        String codeFoundByEmail = redisUtil.getData(email);  
        System.out.println(codeFoundByEmail);  
        if (codeFoundByEmail == null) {  
            return false;  
        }  
        return codeFoundByEmail.equals(code);  
    }  
  
    public String makeMemberId(String email) throws NoSuchAlgorithmException {  
        MessageDigest md = MessageDigest.getInstance("SHA-256");  
        md.update(email.getBytes());  
        md.update(LocalDateTime.now().toString().getBytes());  
        return Arrays.toString(md.digest());  
    } 
}
