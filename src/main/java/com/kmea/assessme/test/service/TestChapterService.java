package com.kmea.assessme.test.service;

import com.kmea.assessme.test.entity.TestChapter;
import com.kmea.assessme.test.repository.TestChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestChapterService {

    @Autowired
    TestChapterRepository testChapterRepository;

    public TestChapter createTestChapter(TestChapter testChapter) {
        return testChapterRepository.save(testChapter);
    }

    public List<TestChapter> getTestChapterByTestId(Long testId) {
        return testChapterRepository.getByTestId(testId);
    }

}
