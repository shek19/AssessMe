package com.kmea.assessme.test.repository;

import com.kmea.assessme.test.entity.Chapter;
import com.kmea.assessme.test.entity.DifficultyLevel;
import com.kmea.assessme.test.entity.Questionnaire;
import com.kmea.assessme.test.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Long> {

    List<Questionnaire> findByChapter(Chapter chapter);

    List<Questionnaire> findByChapter_Subject(Subject subject);

    List<Questionnaire> findByChapterAndDifficultyLevel(Chapter chapter, DifficultyLevel difficultyLevel);

}
