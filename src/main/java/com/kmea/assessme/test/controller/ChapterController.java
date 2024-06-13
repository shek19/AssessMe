package com.kmea.assessme.test.controller;

import com.kmea.assessme.common.exception.EntityNotFoundException;
import com.kmea.assessme.test.entity.Chapter;
import com.kmea.assessme.test.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChapterController {

    @Autowired
    ChapterService chapterService;

    @GetMapping(value = "/subjects/{id}/chapters", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Chapter>> getChaptersBySubjectId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(chapterService.getChaptersBySubjectId(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }



    @GetMapping(value = "/chapters/all")
    public List<Chapter> getAllChapters(){
        return chapterService.getAllChapters();
    }

}
