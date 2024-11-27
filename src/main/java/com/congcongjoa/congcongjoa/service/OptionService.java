package com.congcongjoa.congcongjoa.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.congcongjoa.congcongjoa.dto.OptionDTO;
import com.congcongjoa.congcongjoa.entity.Option;
import com.congcongjoa.congcongjoa.enums.BooleanStatus;
import com.congcongjoa.congcongjoa.repository.OptionRepository;

@Service
public class OptionService {

    @Autowired
    private OptionRepository optionRepository;

    public boolean regOption(OptionDTO optionDTO){
        try {
            Option option = Option.builder()
                .opName(optionDTO.getOpName())
                .opPrice(optionDTO.getOpPrice())
                .opStatus(BooleanStatus.TRUE)
                .build();

            optionRepository.save(option);

            return true;

        } catch (Exception e) {

            return false;

        }

    }
    
}
