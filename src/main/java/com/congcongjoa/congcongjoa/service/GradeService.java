package com.congcongjoa.congcongjoa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.congcongjoa.congcongjoa.repository.GradeRepository;

@Service
public class GradeService {
    
    @Autowired
    private GradeRepository gradeRepository;
    
}
