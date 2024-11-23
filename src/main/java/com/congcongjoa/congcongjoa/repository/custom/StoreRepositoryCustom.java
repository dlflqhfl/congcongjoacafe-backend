package com.congcongjoa.congcongjoa.repository.custom;

import com.congcongjoa.congcongjoa.dto.StoreDTO;
import com.congcongjoa.congcongjoa.entity.Store;

import java.util.List;
import java.util.Optional;

public interface StoreRepositoryCustom {
    List<StoreDTO> findSName();

}
