package com.congcongjoa.congcongjoa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.congcongjoa.congcongjoa.RsData.RsData;
import com.congcongjoa.congcongjoa.dto.AllergyDTO;
import com.congcongjoa.congcongjoa.dto.MenuDTO;
import com.congcongjoa.congcongjoa.dto.MenuOptionDTO;
import com.congcongjoa.congcongjoa.dto.NutritionDTO;
import com.congcongjoa.congcongjoa.dto.OptionDTO;
import com.congcongjoa.congcongjoa.dto.custom.RegMenuOptionDTO;
import com.congcongjoa.congcongjoa.dto.custom.RegStoreDTO;
import com.congcongjoa.congcongjoa.entity.MenuOption;
import com.congcongjoa.congcongjoa.enums.ResponseCode;
import com.congcongjoa.congcongjoa.service.AwsS3Service;
import com.congcongjoa.congcongjoa.service.MenuOptionService;
import com.congcongjoa.congcongjoa.service.MenuService;
import com.congcongjoa.congcongjoa.service.OptionService;
import com.congcongjoa.congcongjoa.service.StoreService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private StoreService storeService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private OptionService optionService;

    @Autowired
    private MenuOptionService menuOptionService;

    @Autowired
    private AwsS3Service awsS3Service;

    @GetMapping("/checkStoreName")
    public RsData<String> checkStoreName(@RequestParam String storeName) {

        System.out.println("storeCode:"+storeName);
        
        boolean result = storeService.checkStoreName(storeName);

        System.out.println("result:"+result);

        if (result) {
            System.out.println("성공");
            return ResponseCode.OK.toRsData(null);
        } else {
            return ResponseCode.USER_ALREADY_EXIST.toRsData(null);
        }
        
    }

    @GetMapping("/checkStoreCode")
    public RsData<String> checkStoreCode(@RequestParam String storeCode) {

        System.out.println("storeCode:"+storeCode);
        
        boolean result = storeService.checkStoreCode(storeCode);

        System.out.println("result:"+result);

        if (result) {
            System.out.println("성공");
            return ResponseCode.OK.toRsData(null);
        } else {
            return ResponseCode.USER_ALREADY_EXIST.toRsData(null);
        }
        
    }

    @PostMapping("/regStore")
    public RsData<String> regStore(@Valid @RequestBody RegStoreDTO regStoreDTO) {
        
        boolean result = storeService.regStore(regStoreDTO);

        if (result) {
            return ResponseCode.OK.toRsData(null);
        } else {
            return ResponseCode.USER_ALREADY_EXIST.toRsData(null);
        }
    }

    @PostMapping("/regMenu")
    public RsData<String> registerMenu(@RequestPart("menu") MenuDTO menuDTO,
                                       @RequestPart("nutrition") NutritionDTO nutritionDTO,
                                       @RequestPart("allergy") AllergyDTO allergyDTO,
                                       @RequestPart("images") List<MultipartFile> images,
                                       @RequestPart("main") List<Boolean> mainList) {

        menuDTO.setNutrition(nutritionDTO);
        menuDTO.setAllergy(allergyDTO);       

        System.out.println("nutritionDTO:"+nutritionDTO);
        System.out.println("allergyDTO:"+allergyDTO);
        
        List<String> uploadedFileNames = new ArrayList<>();
                                    
        if (images != null) {
            for (MultipartFile file : images) {
                if (file != null && file.getSize() > 0) {
                    System.out.println("파일이름 : " + file.getOriginalFilename());
                    try {
                        // 파일을 S3 버킷의 menu 폴더에 업로드
                        String uploadedFileName = awsS3Service.uploadFile(file, "menu/");
                        System.out.println("업로드된 파일 이름 : " + uploadedFileName);
                        uploadedFileNames.add(uploadedFileName);
                    } catch (Exception e) {
                        System.err.println("파일 업로드 중 오류 발생: " + e.getMessage());
                        return ResponseCode.INTERNAL_SERVER_ERROR.toRsData("파일 업로드 중 오류 발생");
                    }
                }
            }
        }

        boolean result = menuService.regMenu(menuDTO, nutritionDTO, allergyDTO, uploadedFileNames, mainList);

        if (result) {
            return ResponseCode.OK.toRsData(null);
        } else {
            return ResponseCode.USER_ALREADY_EXIST.toRsData(null);
        }
    }

    @GetMapping("/menuList")
    public RsData<List<MenuDTO>> getMenuList() {

        List<MenuDTO> menuDTO = menuService.getAllMenu();

        return ResponseCode.OK.toRsData(menuDTO);
    }

    @PostMapping("/regOption")
    public RsData<String> regOption(@Valid @RequestBody OptionDTO optionDTO) {
        
        boolean result = optionService.regOption(optionDTO);

        if (result) {
            return ResponseCode.OK.toRsData(null);
        } else {
            return ResponseCode.USER_ALREADY_EXIST.toRsData(null);
        }
    }

    @GetMapping("/optionList")
    public RsData<List<OptionDTO>> getOptionList() {

        List<OptionDTO> optionDTO = optionService.getAllOption();

        return ResponseCode.OK.toRsData(optionDTO);
    }

    @PostMapping("/regMenuOption")
    public RsData<String> regMenuOption(@RequestBody RegMenuOptionDTO regMenuOption) {
        
        boolean result = menuOptionService.regMenuOption(regMenuOption);

        if (result) {
            return ResponseCode.OK.toRsData(null);
        } else {
            return ResponseCode.USER_ALREADY_EXIST.toRsData(null);
        }
    }

    @GetMapping("/menuOptionList")
    public RsData<List<MenuOptionDTO>> menuOptionList(@RequestParam Long mnId) {
        
        List<MenuOptionDTO>  menuOptionDTO = menuOptionService.getMenuOptionList(mnId);

        return ResponseCode.OK.toRsData(menuOptionDTO);
        
    }
    
}
