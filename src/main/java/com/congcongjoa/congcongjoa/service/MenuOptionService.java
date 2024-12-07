package com.congcongjoa.congcongjoa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.congcongjoa.congcongjoa.dto.MenuOptionDTO;
import com.congcongjoa.congcongjoa.dto.custom.RegMenuOptionDTO;
import com.congcongjoa.congcongjoa.entity.Menu;
import com.congcongjoa.congcongjoa.entity.MenuOption;
import com.congcongjoa.congcongjoa.entity.Option;
import com.congcongjoa.congcongjoa.mapper.MenuOptionMapper;
import com.congcongjoa.congcongjoa.repository.MenuOptionRepository;
import com.congcongjoa.congcongjoa.repository.MenuRepository;
import com.congcongjoa.congcongjoa.repository.OptionRepository;

@Service
public class MenuOptionService {

    @Autowired
    private MenuOptionRepository menuOptionRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private OptionRepository optionRepository;

    public boolean regMenuOption(RegMenuOptionDTO regMenuOption) {
        try {
            Menu menu = menuRepository.findById(regMenuOption.getMnId()).get();
            for(Long id : regMenuOption.getOpId()) {
                Option option = optionRepository.findById(id).get();
                MenuOption menuOption = MenuOption.builder()
                    .menu(menu)
                    .option(option)
                    .build();
            menuOptionRepository.save(menuOption);
            }
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public List<MenuOptionDTO> getMenuOptionList(Long mnId) {

        return MenuOptionMapper.INSTANCE.toMenuOptionDTOList(menuOptionRepository.findByMenuId(mnId));

    }
    
}
