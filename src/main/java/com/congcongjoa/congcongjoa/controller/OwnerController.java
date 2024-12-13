package com.congcongjoa.congcongjoa.controller;
import com.congcongjoa.congcongjoa.RsData.RsData;
import com.congcongjoa.congcongjoa.dto.StoreDTO;
import com.congcongjoa.congcongjoa.enums.ResponseCode;
import com.congcongjoa.congcongjoa.service.AwsS3Service;
import com.congcongjoa.congcongjoa.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/owner")
public class OwnerController {

    @Autowired
    private StoreService storeService;

    @Autowired
    private AwsS3Service awsS3Service;

    @PostMapping("/register")
    public RsData<String> setupStore(@RequestPart(value = "store") StoreDTO storeDTO,
                                     @RequestPart(value = "images") List<MultipartFile> images,
                                     @RequestPart(value = "mainImageIndex") String mainImageIndex) {
        Integer mainImageIndexInt = Integer.parseInt(mainImageIndex);

        System.out.println(mainImageIndexInt);
        System.out.println(images);
        List<String> uploadedFileNames = new ArrayList<>();
        try {

            if (images != null) {
                for (MultipartFile file : images) {
                    if (file != null && file.getSize() > 0) {
                        System.out.println("파일이름 : " + file.getOriginalFilename());
                        // 파일을 S3 버킷의 menu 폴더에 업로드
                        String uploadedFileName = awsS3Service.uploadFile(file, "store/");
                        System.out.println("업로드된 파일 이름 : " + uploadedFileName);
                        uploadedFileNames.add(uploadedFileName);
                    }
                }
            }

            boolean reg = storeService.registerStoreWithImages(uploadedFileNames, mainImageIndexInt, storeDTO);

            if (!reg) {
                throw new IllegalStateException("Store registration failed: User already exists");
            }
            return ResponseCode.OK.toRsData("저장 완료");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            awsS3Service.rollbackFile(uploadedFileNames, "store/");
            return ResponseCode.INTERNAL_SERVER_ERROR.toRsData(e.getMessage());
        }
    }
}