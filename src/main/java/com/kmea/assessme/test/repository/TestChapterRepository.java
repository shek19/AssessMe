package com.kmea.assessme.test.repository;

import com.kmea.assessme.test.entity.TestChapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestChapterRepository extends JpaRepository<TestChapter, Long> {

    List<TestChapter> getByTestId(Long testId);

}
