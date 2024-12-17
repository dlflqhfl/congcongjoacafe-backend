package com.congcongjoa.congcongjoa.controller;

import com.congcongjoa.congcongjoa.RsData.RsData;
import com.congcongjoa.congcongjoa.dto.StoreDTO;
import com.congcongjoa.congcongjoa.enums.ResponseCode;
import com.congcongjoa.congcongjoa.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public/owner")
public class PublicOwnerController {

    @Autowired
    private StoreService storeService;

    @GetMapping("/stores")
    @Operation(summary = "지점 조회", description = "모든 지점의 키값, 지점명을 반환합니다.")
    public RsData<List<StoreDTO>> getStores() {
        try {
            List<StoreDTO> stores = storeService.findIdAndSName();
            return ResponseCode.OK.toRsData(stores);
        } catch (Exception e) {
            return ResponseCode.INTERNAL_SERVER_ERROR.toRsData(null);
        }
    }
}
