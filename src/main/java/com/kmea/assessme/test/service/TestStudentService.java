package com.kmea.assessme.test.service;

import com.kmea.assessme.test.entity.TestStudent;
import com.kmea.assessme.test.repository.TestStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestStudentService {

    @Autowired TestStudentRepository testStudentRepository;

    public TestStudent createTestStudent(TestStudent testStudent) {
        return testStudentRepository.save(testStudent);
    }

}
