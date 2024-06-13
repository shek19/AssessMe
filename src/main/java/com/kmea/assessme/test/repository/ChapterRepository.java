package com.kmea.assessme.test.repository;

import com.kmea.assessme.test.entity.Chapter;
import com.kmea.assessme.test.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter,Long> {
    List<Chapter> findBySubject(Subject subject);
}
