package com.kmea.assessme.test.service;

import com.kmea.assessme.test.entity.StudentResult;
import com.kmea.assessme.test.repository.StudentResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentResultService {

    @Autowired
    StudentResultRepository studentResultRepository;

    public void createStudentResult(StudentResult studentResult) {
        studentResultRepository.save(studentResult);
    }

}
