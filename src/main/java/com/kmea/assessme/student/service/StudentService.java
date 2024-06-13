package com.kmea.assessme.student.service;

import com.kmea.assessme.common.entity.Batch;
import com.kmea.assessme.common.exception.EntityNotFoundException;
import com.kmea.assessme.common.service.BatchService;
import com.kmea.assessme.student.entity.Student;
import com.kmea.assessme.student.repository.StudentRepository;
import com.kmea.assessme.user.entity.User;
import com.kmea.assessme.user.enumeration.UserType;
import com.kmea.assessme.user.exception.ExistingEmailException;
import com.kmea.assessme.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    UserService userService;

    @Autowired
    BatchService batchService;

    public Student createStudent(Student student) throws ExistingEmailException, EntityNotFoundException {
        // Create a new user
        User user = new User();
        user.setEmail(student.getEmail());
        user.setPassword("Welcome123");
        user.setUserType(UserType.STUDENT);

        // Create the user
        userService.createUser(user);

        // Save the student entity
        studentRepository.save(student);

        // Associate the student with the user and update the user
        user.setStudent(student);
        userService.updateUser(user.getId(), user);

        return student;
    }

    public List<Student> getAllStudentsByBatch(Long id) throws EntityNotFoundException {
        Batch batch = batchService.getBatchById(id);
        return studentRepository.findByBatch(batch);
    }

}
