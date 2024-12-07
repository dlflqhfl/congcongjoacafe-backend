package com.congcongjoa.congcongjoa.service;

import java.util.List;

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
    
}
