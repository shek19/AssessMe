package com.kmea.assessme.test.controller;

import com.kmea.assessme.common.exception.EntityNotFoundException;
import com.kmea.assessme.test.entity.Test;
import com.kmea.assessme.test.pojo.*;
import com.kmea.assessme.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @PostMapping("/tests/create")
    public ResponseEntity<Test> createTest(@RequestBody TestDto testDto) {
        Test createdTest = testService.createTest(testDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTest);
    }

    @PostMapping("/tests/assign-students")
    public ResponseEntity<HttpStatus> assignStudentsForTest(@RequestBody TestStudentDto testStudentDto) {
        testService.assignStudentsForTest(testStudentDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/tests")
    public ResponseEntity<List<Test>> getAllTests() {
        List<Test> tests = testService.getAllTests();
        return ResponseEntity.ok(tests);
    }

    @GetMapping("/upcoming-tests")
    public ResponseEntity<List<Test>> getUpcomingTests() {
        List<Test> upcomingTests = testService.getFutureTests();
        return new ResponseEntity<>(upcomingTests, HttpStatus.OK);
    }

    @GetMapping("tests/{id}/questions")
    public ResponseEntity<List<QuestionnaireDto>> prepareQuestionsForTest(@PathVariable Long id) {
        return ResponseEntity.ok(testService.prepareQuestionsForTest(id));
    }

    @PostMapping("tests/evaluate")
    public ResponseEntity<ResultDto> evaluateTest(@RequestBody StudentAnswerDto studentAnswerDto) {
        try {
            return ResponseEntity.ok(testService.evaluateTest(studentAnswerDto));
        } catch (IOException | EntityNotFoundException e) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

}
