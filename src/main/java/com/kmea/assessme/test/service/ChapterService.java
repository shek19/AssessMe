package com.kmea.assessme.test.service;

import com.kmea.assessme.common.exception.EntityNotFoundException;
import com.kmea.assessme.test.entity.Chapter;
import com.kmea.assessme.test.repository.ChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapterService {

    @Autowired
    ChapterRepository chapterRepository;

    @Autowired
    SubjectService subjectService;

    public List<Chapter> getChaptersBySubjectId(Long subjectId) throws EntityNotFoundException {
        return chapterRepository.findBySubject(subjectService.getSubjectById(subjectId));
    }

    public Chapter getChapterById(Long id) throws EntityNotFoundException {
        return chapterRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Chapter with id " + id + " not found"));
    }

    public List<Chapter> getAllChapters(){
        return chapterRepository.findAll();
    }
}
