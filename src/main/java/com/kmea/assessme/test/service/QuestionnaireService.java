package com.kmea.assessme.test.service;

import com.kmea.assessme.common.exception.EntityNotFoundException;
import com.kmea.assessme.test.entity.Chapter;
import com.kmea.assessme.test.entity.DifficultyLevel;
import com.kmea.assessme.test.entity.Questionnaire;
import com.kmea.assessme.test.entity.Subject;
import com.kmea.assessme.test.pojo.QuestionnaireDto;
import com.kmea.assessme.test.repository.QuestionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class QuestionnaireService {

    @Autowired
    QuestionnaireRepository questionnaireRepository;

    @Autowired
    ChapterService chapterService;

    @Autowired
    SubjectService subjectService;

    private QuestionnaireDto getDto(Questionnaire questionnaire) {
        QuestionnaireDto questionnaireDto = new QuestionnaireDto();
        questionnaireDto.setId(questionnaire.getId());
        questionnaireDto.setQuestion(questionnaire.getQuestion());
        questionnaireDto.setOption1(questionnaire.getOption1());
        questionnaireDto.setOption2(questionnaire.getOption2());
        questionnaireDto.setOption3(questionnaire.getOption3());
        questionnaireDto.setOption4(questionnaire.getOption4());
        questionnaireDto.setMarks(questionnaire.getDifficultyLevel().getMark());
        return questionnaireDto;
    }

    public Questionnaire getQuestionnaireById(Long id) throws EntityNotFoundException {
        return questionnaireRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Questionnaire with id " + id + " not found"));
    }

    public Questionnaire saveQuestionnaire(Questionnaire questionnaire) {
        return questionnaireRepository.save(questionnaire);
    }

    public List<Questionnaire> filterQuestionnaires(Long chapterId, Long subjectId) throws EntityNotFoundException {
        if (subjectId != null && chapterId != null) {
            Chapter chapter = chapterService.getChapterById(chapterId);
            return questionnaireRepository.findByChapter(chapter);
        } else if (subjectId != null) {
            Subject subject = subjectService.getSubjectById(subjectId);
            return questionnaireRepository.findByChapter_Subject(subject);
        } else {
            return questionnaireRepository.findAll();
        }
    }

    public List<Questionnaire> getAllQuestionnairesByChapterAndDifficultyLevel(Chapter chapter, DifficultyLevel difficultyLevel) {
        return questionnaireRepository.findByChapterAndDifficultyLevel(chapter, difficultyLevel);
    }

    public List<QuestionnaireDto> getQuestionnareDtoList(List<Questionnaire> allQuestions) {
        List<QuestionnaireDto> questionnaireDtoList = new ArrayList<>();
        for (Questionnaire questionnaire : allQuestions) {
            questionnaireDtoList.add(getDto(questionnaire));
        }
        return questionnaireDtoList;
    }

}
