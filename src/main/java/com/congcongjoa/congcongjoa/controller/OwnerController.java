package com.congcongjoa.congcongjoa.controller;
import com.congcongjoa.congcongjoa.RsData.RsData;
import com.congcongjoa.congcongjoa.dto.StoreDTO;
import com.congcongjoa.congcongjoa.enums.ResponseCode;
import com.congcongjoa.congcongjoa.service.AwsS3Service;
import com.congcongjoa.congcongjoa.service.ImageService;
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
    private ImageService imageService;

    @Autowired
    private AwsS3Service awsS3Service;

    @PostMapping("/register")
    public RsData<String> setupStore(@RequestPart(value = "store") StoreDTO storeDTO,
                                     @RequestPart(value = "images") List<MultipartFile> images,
                                     @RequestPart(value = "mainImageIndex") String mainImageIndex) {
        Integer mainImageIndexInt = Integer.parseInt(mainImageIndex);
        System.out.println("mainImageIndex : " + mainImageIndex);
        System.out.println("images : " + images);
        System.out.println("storeDTO : " + storeDTO);

        System.out.println(storeDTO.getSAddress().getDetail());
        System.out.println(storeDTO.getSAddress().getPostCode());
        System.out.println(storeDTO.getSAddress().getStreet());
        try {
            List<String> uploadedFileNames = new ArrayList<>();

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

            imageService.regStoreImages(uploadedFileNames, mainImageIndexInt);
            return ResponseCode.OK.toRsData("저장 완료");
        } catch (Exception e) {
            System.err.println("오류 발생: " + e.getMessage());
            return ResponseCode.INTERNAL_SERVER_ERROR.toRsData("처리 중 오류 발생");
        }
    }
}