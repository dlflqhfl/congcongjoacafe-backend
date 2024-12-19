package com.congcongjoa.congcongjoa.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.congcongjoa.congcongjoa.dto.OptionDTO;
import com.congcongjoa.congcongjoa.entity.Option;
import com.congcongjoa.congcongjoa.enums.BooleanStatus;
import com.congcongjoa.congcongjoa.mapper.OptionMapper;
import com.congcongjoa.congcongjoa.repository.OptionRepository;

@Service
public class OptionService {

    @Autowired
    private OptionRepository optionRepository;

    public boolean checkOptionName(String optionName) {
        try {
            Option option = optionRepository.findByopName(optionName);
            return option == null;
        } catch (Exception e) {
            System.err.println("Error checking option code: " + e.getMessage());
            return false;
        }
    }

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
    
    public List<OptionDTO> getAllOption() {

        return OptionMapper.INSTANCE.toOptionDTOList(optionRepository.findOptionAll());

    }

    public boolean updateOption(Long id, OptionDTO optionDTO) {

        System.out.println("옵션 수정 서비스 왔다");

        try {
            Option option = optionRepository.findById(id).get();

            System.out.println(option.getId());
            System.out.println(option.getOpName());
            System.out.println(option.getOpPrice());

            Option updateOption = option.toBuilder()
                .opName(optionDTO.getOpName())
                .opPrice(optionDTO.getOpPrice())
                .build();

            System.out.println(updateOption.getId());
            System.out.println(updateOption.getOpName());
            System.out.println(updateOption.getOpPrice());

            optionRepository.save(updateOption);

            return true;

        } catch (Exception e) {

            return false;

        }
    }

    public boolean deleteOption(Long id) {
        try {
            Option option = optionRepository.findById(id).get();

            Option deleteOption = option.toBuilder()
                .opStatus(BooleanStatus.FALSE)
                .build();

            optionRepository.save(deleteOption);

            return true;

        } catch (Exception e) {

            return false;

        }
    }

}
