package com.congcongjoa.congcongjoa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.congcongjoa.congcongjoa.repository.ImageRepository;

import java.util.List;

@Service
public class ImageService {
    
    @Autowired
    private ImageRepository imageRepository;


    public void regStoreImages(List<String> uploadedFileNames, Integer mainImageIndex) {

    }
}
