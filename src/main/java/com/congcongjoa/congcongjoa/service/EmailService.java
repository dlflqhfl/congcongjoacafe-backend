package com.congcongjoa.congcongjoa.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import com.congcongjoa.congcongjoa.dto.custom.RegStoreDTO;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String configEmail;

    @Value("${upload}")
    private String upload;

    private String createdCode() {
        int leftLimit = 48; // number '0'
        int rightLimit = 122; // alphabet 'z'
        int targetStringLength = 6;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
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
        templateResolver.setCharacterEncoding("UTF-8");

        templateEngine.setTemplateResolver(templateResolver);

        return templateEngine.process("reset_mail", context);
    }

    // 메일 반환
    private MimeMessage createEmailForm(String email, String password) throws MessagingException, IOException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(email);
        helper.setSubject("CongCongJoaCafe 비밀번호 안내드립니다.");
        helper.setFrom(configEmail);
        helper.setText(setContext(password), true);

         // S3에서 이미지 다운로드
         String imageUrl = upload+"logo/logo.png";
         byte[] imageBytes = downloadImage(imageUrl);
         ByteArrayResource imageResource = new ByteArrayResource(imageBytes);
         helper.addInline("logoImage", imageResource, "image/png");   

        return message;
    }

    // 메일 보내기
    public String sendEmail(String email) throws MessagingException, IOException {
            String password = createdCode();
            MimeMessage emailForm = createEmailForm(email,password);
            mailSender.send(emailForm);
            return password;
    }

    public boolean sendRegEmail(RegStoreDTO store) throws MessagingException, IOException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(store.getEmail());
        helper.setSubject("CongCongJoaCafe 매장 등록 안내");
        helper.setFrom(configEmail);

        String imageUrl = upload+"logo/logo.png";
        byte[] imageBytes = downloadImage(imageUrl);
        ByteArrayResource imageResource = new ByteArrayResource(imageBytes);

        Context context = new Context();
        context.setVariable("store", store);

        TemplateEngine templateEngine = new TemplateEngine();
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCacheable(false);
        templateResolver.setCharacterEncoding("UTF-8");
        templateEngine.setTemplateResolver(templateResolver);

        String htmlContent = templateEngine.process("regist_mail", context);
        helper.setText(htmlContent, true);
        helper.addInline("logoImage", imageResource, "image/png");
        mailSender.send(message);

        return true;
    }

    // 이미지 다운로드 메서드
    private byte[] downloadImage(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        try (InputStream inputStream = connection.getInputStream()) {
            return inputStream.readAllBytes();
        }
    }
    
}
