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
    private List<AllergyDTO> allergies = new ArrayList<>();
    private List<NutritionDTO> nutritions = new ArrayList<>();
    private List<MenuOptionDTO> menuOptions = new ArrayList<>();
    private List<StoreMenuDTO> storeMenus = new ArrayList<>();
    private List<ImageDTO> images = new ArrayList<>();

}
