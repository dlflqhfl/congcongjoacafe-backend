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

    // 모든 매장의 키값과 이름을 가져온다
    public List<StoreDTO> findIdAndSName() {
        return storeRepository.findSName();
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
        try {
            String mainImageFileName = uploadedFileNames.get(mainImageIndex);

            // Store 객체 생성 및 상태 업데이트
            Store newStore = StoreMapper.INSTANCE.toStore(storeDTO).toBuilder()
                    .sStatus(StoreStatus.OPEN)
                    .build();

            // 기존 Store 조회 및 업데이트
            Store existingStore = storeRepository.findBysName(newStore.getSName());
            if (existingStore != null) {
                existingStore.update(newStore);
                storeRepository.save(existingStore);
            }

            // 이미지 생성 및 저장
            for (String uploadedFileName : uploadedFileNames) {
                Image image = createImage(uploadedFileName, uploadedFileName.equals(mainImageFileName), existingStore);
                imageRepository.save(image);
            }

            return true; // 모든 작업 성공 시 true 반환
        } catch (Exception e) {
            e.printStackTrace(); // 로그 출력 (추후 로깅 프레임워크 권장)
            return false; // 예외 발생 시 false 반환
        }
    }

    // 이미지를 생성하는 로직을 별도 메서드로 추출
    private Image createImage(String imageName, boolean isMainImage, Store store) {
        return Image.builder()
                .iName(imageName)
                .store(store)
                .iCate(ICATE.STORE)
                .iMain(isMainImage ? BooleanStatus.TRUE : BooleanStatus.FALSE)
                .iStatus(BooleanStatus.TRUE)
                .build();
    }



}
