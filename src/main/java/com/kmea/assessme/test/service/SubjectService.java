package com.kmea.assessme.test.service;

import com.kmea.assessme.common.exception.EntityNotFoundException;
import com.kmea.assessme.test.entity.Subject;
import com.kmea.assessme.test.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    @Autowired
    SubjectRepository subjectRepository;

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Subject getSubjectById(Long id) throws EntityNotFoundException {
        return subjectRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Subject with id " + id + " not found"));
    }

}
