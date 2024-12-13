package com.congcongjoa.congcongjoa.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AwsS3Service {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    //파일 업로드
    public String uploadFile(MultipartFile file, String path){
        try {
            String newFileName = checkFile(file);

            InputStream fileInputStream = file.getInputStream();

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            metadata.setContentLength(file.getSize());

            amazonS3.putObject(bucketName, path + newFileName, fileInputStream, metadata);
            fileInputStream.close();  // InputStream 을 명시적으로 닫아주는 것이 좋음

            return newFileName;

        }catch (IOException e){
            throw new IllegalArgumentException("S3 파일 업로드에 실패하였습니다.");
        }
    }

    //파일이름과 확장자 체크 및 추출(리턴값 예시 : "wooseok.jpg")
    private static String checkFile(MultipartFile file) {
        String originalFileName = file.getOriginalFilename();
        if (originalFileName == null || originalFileName.isEmpty()) {
            throw new IllegalArgumentException("파일 이름이 비어있습니다.");
        }

        String fileExtension = originalFileName.contains(".")
                ? originalFileName.substring(originalFileName.lastIndexOf('.') + 1)
                : "";

        if (fileExtension.isEmpty()) {
            throw new IllegalArgumentException("파일 확장자가 올바르지 않습니다.");
        }

        // UUID를 사용하여 고유한 파일 이름 생성
        String newFileName = UUID.randomUUID().toString();

        return newFileName + "." + fileExtension;
    }

    public void deleteFile(String path, String fileName) {
        String fullPath = path + fileName;
        amazonS3.deleteObject(bucketName, fullPath);
    }

    public void rollbackFile(List<String> uploadedFileNames, String path) {
        for (String fileName : uploadedFileNames) {
            try {
                deleteFile(path, fileName);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    
}
