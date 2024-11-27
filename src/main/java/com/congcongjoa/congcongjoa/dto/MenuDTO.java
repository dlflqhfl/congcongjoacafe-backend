package com.congcongjoa.congcongjoa.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import com.congcongjoa.congcongjoa.enums.BooleanStatus;
import com.congcongjoa.congcongjoa.enums.MenuCate;
import com.congcongjoa.congcongjoa.enums.Size;

@Data
public class MenuDTO {

    private Long id;
    private MenuCate mnCate;
    private String mnName;
    private String mnDetail;
    private Size mnSize;
    private Long mnPrice;
    private BooleanStatus mnStatus;
    private String mnNone;
    private AllergyDTO allergy;
    private NutritionDTO nutrition;
    private MenuOptionDTO menuOption;
    private StoreMenuDTO storeMenu;
    private List<ImageDTO> images = new ArrayList<>();

}
