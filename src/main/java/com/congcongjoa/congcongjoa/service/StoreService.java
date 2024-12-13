package com.congcongjoa.congcongjoa.service;

import com.congcongjoa.congcongjoa.dto.StoreDTO;
import com.congcongjoa.congcongjoa.entity.Image;
import com.congcongjoa.congcongjoa.entity.Store;
import com.congcongjoa.congcongjoa.enums.BooleanStatus;
import com.congcongjoa.congcongjoa.enums.ICATE;
import com.congcongjoa.congcongjoa.mapper.StoreMapper;
import com.congcongjoa.congcongjoa.repository.ImageRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.congcongjoa.congcongjoa.dto.custom.RegStoreDTO;
import com.congcongjoa.congcongjoa.enums.StoreStatus;
import com.congcongjoa.congcongjoa.repository.StoreRepository;

import java.util.List;


@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ImageRepository imageRepository;
    

    public boolean checkStoreName(String storeName) {
        try {
            Store store = storeRepository.findBysName(storeName);
            return store == null;
        } catch (Exception e) {
            System.err.println("Error checking store code: " + e.getMessage());
            return false;
        }
    }

    public boolean checkStoreCode(String storeCode) {
        try {
            Store store = storeRepository.findBysCode(storeCode);
            return store == null;
        } catch (Exception e) {
            System.err.println("Error checking store code: " + e.getMessage());
            return false;
        }
    }

    public boolean regStore(RegStoreDTO regStoreDTO) {
        try {
            
            Store store = Store.builder()
                .sCode(regStoreDTO.getStoreCode())
                .sName(regStoreDTO.getName())
                .sPw(regStoreDTO.getInitialPassword())
                .sStatus(StoreStatus.REGISTERED)
                .build();
    
            storeRepository.save(store);

            return true;

        } catch (Exception e) {

            return false;

        }
    }

    @Transactional
    public boolean registerStoreWithImages(List<String> uploadedFileNames, Integer mainImageIndex, StoreDTO storeDTO) {

        String mainImageFileName = uploadedFileNames.get(mainImageIndex);
        System.out.println(mainImageFileName);

        Store store = StoreMapper.INSTANCE.toStore(storeDTO);

        store = store.toBuilder()
                .sStatus(StoreStatus.OPEN)
                .build();

        System.out.println(store);

        //sotre id찾기
        Store storeId = storeRepository.findBysName(store.getSName());


        if (storeId != null) {
            storeId.update(store);
             storeRepository.save(storeId);
        }

        //이미지 저장
        for (String uploadedFileName : uploadedFileNames) {
            Image image = null;

            if (uploadedFileName.equals(mainImageFileName)) {
                image = Image.builder()
                        .iName(mainImageFileName) // 메인 이미지
                        .store(storeId)
                        .iCate(ICATE.STORE)
                        .iMain(BooleanStatus.TRUE)
                        .iStatus(BooleanStatus.TRUE)
                        .build();
            } else {
                image = Image.builder()
                        .iName(uploadedFileName) // else에서는 uploadedFileName 사용
                        .store(storeId)
                        .iCate(ICATE.STORE)
                        .iMain(BooleanStatus.FALSE)
                        .iStatus(BooleanStatus.TRUE)
                        .build();
            }

            // 새로 생성한 Image 객체를 데이터베이스에 저장
            if (image != null) {
                imageRepository.save(image);
            }
        }
        return false;
    }
        // 모든 매장의 키값과 이름을 가져온다
    public List<StoreDTO> findIdAndSName() {

        return storeRepository.findSName();
    }


}
