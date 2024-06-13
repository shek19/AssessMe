package com.kmea.assessme.test.service;

import com.kmea.assessme.test.entity.DifficultyLevel;
import com.kmea.assessme.test.repository.DifficultyLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DifficultyLevelService {

    @Autowired
    DifficultyLevelRepository difficultyLevelRepository;

    DifficultyLevel getDifficultyLevelByLevel(String level){
        return difficultyLevelRepository.findByLevel(level);
    }

    public List<DifficultyLevel> getAllDifficultyLevel(){
        return difficultyLevelRepository.findAll();
    }

}
