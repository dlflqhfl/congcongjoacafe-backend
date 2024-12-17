package com.congcongjoa.congcongjoa.service;

import java.util.List;
import java.util.stream.Collectors;

import com.congcongjoa.congcongjoa.enums.ICATE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.congcongjoa.congcongjoa.dto.AllergyDTO;
import com.congcongjoa.congcongjoa.dto.MenuDTO;
import com.congcongjoa.congcongjoa.dto.NutritionDTO;
import com.congcongjoa.congcongjoa.entity.Allergy;
import com.congcongjoa.congcongjoa.entity.Image;
import com.congcongjoa.congcongjoa.entity.Menu;
import com.congcongjoa.congcongjoa.entity.Nutrition;
import com.congcongjoa.congcongjoa.enums.BooleanStatus;
import com.congcongjoa.congcongjoa.enums.MenuCate;
import com.congcongjoa.congcongjoa.enums.Size;
import com.congcongjoa.congcongjoa.mapper.MenuMapper;
import com.congcongjoa.congcongjoa.repository.AllergyRepository;
import com.congcongjoa.congcongjoa.repository.ImageRepository;
import com.congcongjoa.congcongjoa.repository.MenuRepository;
import com.congcongjoa.congcongjoa.repository.NutritionRepository;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private NutritionRepository nutritionRepository;

    @Autowired
    private AllergyRepository allergyRepository;

    @Autowired
    private ImageRepository imageRepository;

    public boolean regMenu(MenuDTO menuDTO,  NutritionDTO nutritionDTO,
                            AllergyDTO allergyDTO, List<String> uploadedFileNames, List<Boolean> mainList){
        try {
            
            Menu menu = Menu.builder()
                .mnName(menuDTO.getMnName())
                .mnPrice(menuDTO.getMnPrice())
                .mnCate(MenuCate.valueOf(menuDTO.getMnCate().name()))
                .mnSize(Size.valueOf(menuDTO.getMnSize().name()))
                .mnDetail(menuDTO.getMnDetail())
                .mnStatus(BooleanStatus.valueOf(menuDTO.getMnStatus().name()))
                .build();

            menuRepository.save(menu);
            
            Nutrition nutrition = Nutrition.builder()
                .menu(menu)
                .nOne(nutritionDTO.getNOne())
                .nCal(nutritionDTO.getNCal())
                .nCarbo(nutritionDTO.getNCarbo())
                .nProtein(nutritionDTO.getNProtein())
                .nFat(nutritionDTO.getNFat())
                .nSalt(nutritionDTO.getNSalt())
                .nCaffeine(nutritionDTO.getNCaffeine())
                .nSugar(nutritionDTO.getNSugar())
                .build();

            nutritionRepository.save(nutrition);

            Allergy allergy = Allergy.builder()
                .menu(menu)
                .aEgg(BooleanStatus.valueOf(allergyDTO.getAEgg().name()))
                .aMilk(BooleanStatus.valueOf(allergyDTO.getAMilk().name()))
                .aSoy(BooleanStatus.valueOf(allergyDTO.getASoy().name()))
                .aWheat(BooleanStatus.valueOf(allergyDTO.getAWheat().name()))
                .build();

            allergyRepository.save(allergy);
            
            for (int i = 0; i < uploadedFileNames.size(); i++) {
                String filename = uploadedFileNames.get(i);
                BooleanStatus main = mainList.get(i) ? BooleanStatus.TRUE : BooleanStatus.FALSE;

                Image image = Image.builder()
                    .menu(menu)
                    .iName(filename)
                    .iCate(ICATE.MENU) // 메뉴
                    .iStatus(BooleanStatus.TRUE)
                    .iMain(main)
                    .build();

                imageRepository.save(image);
            }
        
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public List<MenuDTO> getAllMenu() {

        return MenuMapper.INSTANCE.toMenuDTOList(menuRepository.findAll());
    }
    
    public List<String> getExistingImageNames(Long menuId) {

        Menu menu = menuRepository.findById(menuId).get();

        return imageRepository.findByMenu(menu).stream()
                .map(Image::getIName)
                .collect(Collectors.toList());
    }

    public boolean updateMenu(Long id, MenuDTO menuDTO, NutritionDTO nutritionDTO, AllergyDTO allergyDTO, List<String> uploadedFileNames, List<Boolean> mainList) {
        try {
            Menu menu = menuRepository.findById(id).get();

            menu = menu.toBuilder()
                .mnName(menuDTO.getMnName())
                .mnPrice(menuDTO.getMnPrice())
                .mnCate(MenuCate.valueOf(menuDTO.getMnCate().name()))
                .mnSize(Size.valueOf(menuDTO.getMnSize().name()))
                .mnDetail(menuDTO.getMnDetail())
                .mnStatus(BooleanStatus.valueOf(menuDTO.getMnStatus().name()))
                .build();

            menuRepository.save(menu);

            Nutrition nutrition = nutritionRepository.findByMenu(menu);
            nutrition = nutrition.toBuilder()
                .menu(menu)
                .nOne(nutritionDTO.getNOne())
                .nCal(nutritionDTO.getNCal())
                .nCarbo(nutritionDTO.getNCarbo())
                .nProtein(nutritionDTO.getNProtein())
                .nFat(nutritionDTO.getNFat())
                .nSalt(nutritionDTO.getNSalt())
                .nCaffeine(nutritionDTO.getNCaffeine())
                .nSugar(nutritionDTO.getNSugar())
                .build();

            nutritionRepository.save(nutrition);

            Allergy allergy = allergyRepository.findByMenu(menu);
            allergy = allergy.toBuilder()
                .menu(menu)
                .aEgg(BooleanStatus.valueOf(allergyDTO.getAEgg().name()))
                .aMilk(BooleanStatus.valueOf(allergyDTO.getAMilk().name()))
                .aSoy(BooleanStatus.valueOf(allergyDTO.getASoy().name()))
                .aWheat(BooleanStatus.valueOf(allergyDTO.getAWheat().name()))
                .build();

            allergyRepository.save(allergy);

            List<String> existingImageNames = getExistingImageNames(id);

            // 기존 이미지 중 업로드된 이미지에 없는 것들은 상태를 FALSE로 변경
            for (String existingImageName : existingImageNames) {
                if (!uploadedFileNames.contains(existingImageName)) {
                    Image image = imageRepository.findByiNameAndMenu(existingImageName, menu);
                    if (image != null) {
                        image = image.toBuilder()
                            .iStatus(BooleanStatus.FALSE)
                            .build();
                        imageRepository.save(image);
                    }
                }
            }

            // 업로드된 이미지 중 기존 이미지에 없는 것들은 새로 추가
            for (int i = 0; i < uploadedFileNames.size(); i++) {
                String filename = uploadedFileNames.get(i);
                if (!existingImageNames.contains(filename)) {
                    BooleanStatus main = mainList.get(i) ? BooleanStatus.TRUE : BooleanStatus.FALSE;

                    Image image = Image.builder()
                        .menu(menu)
                        .iName(filename)
                        .iCate(ICATE.MENU) // 메뉴
                        .iStatus(BooleanStatus.TRUE)
                        .iMain(main)
                        .build();

                    imageRepository.save(image);
                }
            }

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteMenu(Long id) {
        try {
            Menu menu = menuRepository.findById(id).get();

           Menu deleteMenu = menu.toBuilder()
                .mnStatus(BooleanStatus.FALSE)
                .build();

            menuRepository.save(deleteMenu);

            return true;

        } catch (Exception e) {

            return false;

        }
    }
    
}
