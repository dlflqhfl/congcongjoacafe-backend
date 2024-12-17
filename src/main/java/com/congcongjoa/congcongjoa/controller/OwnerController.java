package com.congcongjoa.congcongjoa.controller;
import com.congcongjoa.congcongjoa.RsData.RsData;
import com.congcongjoa.congcongjoa.dto.StoreDTO;
import com.congcongjoa.congcongjoa.dto.custom.OwnerDashboardStatsDTO;
import com.congcongjoa.congcongjoa.enums.ResponseCode;
import com.congcongjoa.congcongjoa.service.AwsS3Service;
import com.congcongjoa.congcongjoa.service.OrderService;
import com.congcongjoa.congcongjoa.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
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
    private OrderService orderService;

    @Autowired
    private AwsS3Service awsS3Service;

    @PostMapping("/register")
    @Operation(summary = "매점 등록", description = "스토어 정보 이미지를 리엑트 서버로 부터 받아와 매점 정보와 이미지 등록")
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

            return ResponseCode.OK.toRsData("매장 정보 저장 완료");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            awsS3Service.rollbackFile(uploadedFileNames, "store/");
            return ResponseCode.INTERNAL_SERVER_ERROR.toRsData(e.getMessage());
        }
    }

    @GetMapping("/dashboard/stats")
    @Operation(summary = "점주 각종 정보들 불러오기", description = "")
    public RsData<String> getStats(String sName) {
        OwnerDashboardStatsDTO ownerDashboardStatsDTO = new OwnerDashboardStatsDTO();

        Long sIdx = storeService.getsIdBySName(sName);

        // 실시간 오늘 주문 수
        ownerDashboardStatsDTO.setOrders(orderService.getTodayOrder(sIdx));

        // 실시간 오늘 매출
        ownerDashboardStatsDTO.setRevenue(orderService.getTodayRevenue(sIdx));

        // 실시간 오늘 제일 많이 팔린 메뉴
        ownerDashboardStatsDTO.setMenu(orderService.getTodayMenus(sIdx));

        return null;
    }
}