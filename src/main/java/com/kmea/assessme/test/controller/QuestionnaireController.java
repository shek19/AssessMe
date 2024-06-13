package com.kmea.assessme.test.controller;

import com.kmea.assessme.common.exception.EntityNotFoundException;
import com.kmea.assessme.test.entity.Questionnaire;
import com.kmea.assessme.test.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuestionnaireController {

    @Autowired
    QuestionnaireService questionnaireService;

    @PostMapping("/questionnaires")
    public ResponseEntity<Questionnaire> createQuestionnaire(@RequestBody Questionnaire questionnaire) {
        return ResponseEntity.ok(questionnaireService.saveQuestionnaire(questionnaire));
    }

    @GetMapping("/questionnaires")
    public ResponseEntity<List<Questionnaire>> getFilteredQuestionnaires(
            @RequestParam(required = false) Long subject,
            @RequestParam(required = false) Long chapter) {
        try {
            return ResponseEntity.ok(questionnaireService.filterQuestionnaires(chapter, subject));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}