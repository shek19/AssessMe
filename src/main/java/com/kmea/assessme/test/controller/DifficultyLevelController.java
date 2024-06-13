package com.kmea.assessme.test.controller;

import com.kmea.assessme.test.entity.DifficultyLevel;
import com.kmea.assessme.test.service.DifficultyLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DifficultyLevelController {

    @Autowired
    DifficultyLevelService difficultyLevelService;

    @GetMapping(value = "/difficulty/all")
    public List<DifficultyLevel> getAllDifficultyLevel(){
        return difficultyLevelService.getAllDifficultyLevel();
    }
}
