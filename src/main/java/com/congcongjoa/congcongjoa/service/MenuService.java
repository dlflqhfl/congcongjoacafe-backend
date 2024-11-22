package com.congcongjoa.congcongjoa.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.congcongjoa.congcongjoa.dto.ImageDTO;
import com.congcongjoa.congcongjoa.dto.MenuDTO;
import com.congcongjoa.congcongjoa.entity.Allergy;
import com.congcongjoa.congcongjoa.entity.Image;
import com.congcongjoa.congcongjoa.entity.Menu;
import com.congcongjoa.congcongjoa.entity.Nutrition;
import com.congcongjoa.congcongjoa.enums.BooleanStatus;
import com.congcongjoa.congcongjoa.enums.MenuCate;
import com.congcongjoa.congcongjoa.enums.Size;
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

    public boolean regMenu(MenuDTO menuDTO){
        try {
            
            Menu menu = new Menu();

            menu.setMnName(menuDTO.getMnName());
            menu.setMnPrice(menuDTO.getMnPrice());
            menu.setMnCate(MenuCate.valueOf(menuDTO.getMnCate().name()));
            menu.setMnSize(Size.valueOf(menuDTO.getMnSize().name()));
            menu.setMnDetail(menuDTO.getMnDetail());
            menu.setMnStatus(BooleanStatus.valueOf(menuDTO.getMnStatus().name()));
            menuRepository.save(menu);
            
            Nutrition nutrition = new Nutrition();
            nutrition.setNOne(menuDTO.getNutrition().getNOne());
            nutrition.setNCal(menuDTO.getNutrition().getNCal());
            nutrition.setNCarbo(menuDTO.getNutrition().getNCarbo());
            nutrition.setNProtein(menuDTO.getNutrition().getNProtein());
            nutrition.setNFat(menuDTO.getNutrition().getNFat());
            nutrition.setNSalt(menuDTO.getNutrition().getNSalt());
            nutrition.setNCaffeine(menuDTO.getNutrition().getNCaffeine());
            nutrition.setNSugar(menuDTO.getNutrition().getNSugar());
            nutrition.setNNone(menuDTO.getNutrition().getNNone());
            nutrition.setMenu(menu);
            nutritionRepository.save(nutrition);

            Allergy allergy = new Allergy();
            allergy.setAEgg(BooleanStatus.valueOf(menuDTO.getAllergy().getAEgg().name()));
            allergy.setAMilk(BooleanStatus.valueOf(menuDTO.getAllergy().getAMilk().name()));
            allergy.setASoy(BooleanStatus.valueOf(menuDTO.getAllergy().getASoy().name()));
            allergy.setAWheat(BooleanStatus.valueOf(menuDTO.getAllergy().getAWheat().name()));
            allergy.setMenu(menu);
            menu.setAllergy(allergy);
            allergyRepository.save(allergy);
            
            for (ImageDTO imageDTO : menuDTO.getImages()) {
            Image image = new Image();
            image.setIName(imageDTO.getIName());
            image.setICate(BooleanStatus.FALSE);//메뉴
            image.setIStatus(BooleanStatus.TRUE);
            image.setMenu(menu);
            imageRepository.save(image);
            }
        
            return true;

        } catch (Exception e) {
            return false;
        }
    }
    
}
