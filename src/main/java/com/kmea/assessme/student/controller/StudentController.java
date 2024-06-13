package com.kmea.assessme.student.controller;

import com.kmea.assessme.common.entity.Batch;
import com.kmea.assessme.common.exception.EntityNotFoundException;
import com.kmea.assessme.common.service.BatchService;
import com.kmea.assessme.student.entity.Student;
import com.kmea.assessme.student.service.StudentService;
import com.kmea.assessme.user.exception.ExistingEmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    BatchService batchService;

    @PostMapping("/students/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        try {
            // Retrieve the Batch object from its ID
            Batch batch = batchService.getBatchById(student.getBatch().getId());
            student.setBatch(batch);  // Set the Batch object in the Student entity
            return ResponseEntity.ok(studentService.createStudent(student));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ExistingEmailException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping("batches/{id}/students")
    public ResponseEntity<List<Student>> getAllStudentsByBatch(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(studentService.getAllStudentsByBatch(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/batches")
    public List<Batch> getAllBatches()
    {
        return batchService.getAllBatch();
    }
}

