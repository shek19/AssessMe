package com.kmea.assessme.test.service;

import com.kmea.assessme.student.entity.Student;
import com.kmea.assessme.test.entity.*;
import com.kmea.assessme.test.enumeration.TestStatus;
import com.kmea.assessme.test.pojo.*;
import com.kmea.assessme.test.repository.SubjectRepository;
import com.kmea.assessme.test.repository.TestRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TestService {

    @Autowired
    TestChapterService testChapterService;

    @Autowired
    TestStudentService testStudentService;

    @Autowired
    DifficultyLevelService difficultyLevelService;

    @Autowired
    QuestionnaireService questionnaireService;

    @Autowired
    TestRepository testRepository;

    @Autowired
    StudentResultService studentResultService;

    @Autowired
    GradeService gradeService;

    @Autowired
    SubjectRepository subjectRepository;


    public Test getEntity(TestDto testDto) {
        Test test = new Test();
        test.setTestName(testDto.getTestName());
        test.setSubject(testDto.getSubject());
        test.setConditions(testDto.getConditions());
        test.setConditions(testDto.getConditions());
        test.setStartTime(testDto.getStartTime());
        test.setDurationInMinutes(testDto.getDurationInMinutes());
        return test;
    }



/*
public Test getEntity(TestDto testDto) {
    // Check if subject ID is provided
    if (testDto.getSubject() == null || testDto.getSubject().getId() == null) {
        throw new IllegalArgumentException("Subject ID cannot be null or empty");
    }

    // Proceed with fetching the subject and creating the test
    Long subjectId = testDto.getSubject().getId();
    Subject subject = subjectRepository.findById(subjectId)
            .orElseThrow(() -> new EntityNotFoundException("Subject not found with id: " + subjectId));

    Test test = new Test();
    test.setTestName(testDto.getTestName());
    test.setSubject(subject);
    test.setConditions(testDto.getConditions());
    test.setStartTime(testDto.getStartTime());
    test.setDurationInMinutes(testDto.getDurationInMinutes());

    return test;
}

 */





    public Test createTest(TestDto testDto) {
       Test test = testRepository.save(getEntity(testDto));
        for (TestChapter testChapter :
                testDto.getTestChapters()) {
            testChapter.setTest(test);
            testChapterService.createTestChapter(testChapter);
        }
        return test;
    }

    public List<Test> getAllTests() {
        return testRepository.findAll();
    }

    public List<Test> getFutureTests() {
        return testRepository.findByStartTimeAfter(LocalDateTime.now());
    }

    public void assignStudentsForTest(TestStudentDto testStudentDto) {
        for (Student student :
                testStudentDto.getStudents()) {
            TestStudent testStudent = new TestStudent();
            testStudent.setTest(testStudentDto.getTest());
            testStudent.setStudent(student);
            testStudentService.createTestStudent(testStudent);
        }
    }

    public Test getTestById(Long id) {
        return testRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Test with id " + id + " not found"));
    }

    public List<QuestionnaireDto> prepareQuestionsForTest(Long testId) {

        List<Questionnaire> allQuestions = new ArrayList<>();

        List<TestChapter> testChapters = testChapterService.getTestChapterByTestId(testId);

        for (TestChapter testChapter : testChapters) {
            Chapter chapter = testChapter.getChapter();
            allQuestions.addAll(getRandomQuestionsByDifficulty(chapter, "EASY", testChapter.getEasyQuestions()));
            allQuestions.addAll(getRandomQuestionsByDifficulty(chapter, "DIFFICULT", testChapter.getDifficultQuestions()));
            allQuestions.addAll(getRandomQuestionsByDifficulty(chapter, "COMPLEX", testChapter.getComplexQuestions()));
        }

        return questionnaireService.getQuestionnareDtoList(allQuestions);
    }

    private List<Questionnaire> getRandomQuestionsByDifficulty(Chapter chapter, String level, int numQuestions) {
        DifficultyLevel difficultyLevel = difficultyLevelService.getDifficultyLevelByLevel(level);
        List<Questionnaire> questions = questionnaireService.getAllQuestionnairesByChapterAndDifficultyLevel(chapter, difficultyLevel);

        Collections.shuffle(questions);

        // Ensure numQuestions doesn't exceed the number of available questions
        int selectedNumQuestions = Math.min(numQuestions, questions.size());

        return questions.subList(0, selectedNumQuestions);
    }

    public ResultDto evaluateTest(StudentAnswerDto studentAnswerDto) throws EntityNotFoundException, IOException, com.kmea.assessme.common.exception.EntityNotFoundException {
        StudentResult studentResult = new StudentResult();
        studentResult.setTest(studentAnswerDto.getTest());
        studentResult.setStudent(studentAnswerDto.getStudent());

        // Evaluate answer sheet to obtain total and acquired marks
        evaluateAnswerSheetToObtainTotalAndAcquiredMark(studentResult, studentAnswerDto.getAnswerDtoList());

        // Calculate percentage and determine grade
        int percentage = (int) Math.round(calculatePercentage(studentResult));
        Grade grade = gradeService.getGradeByPercentage(percentage);
        studentResult.setGrade(grade);

        //new change
        // Set the status based on the percentage
        studentResult.setStatus(percentage > 60 ? TestStatus.PASSED : TestStatus.FAILED);

        // Save the answer sheet
        byte[] serializedAnswerSheet = serializeAnswerDtoList(studentAnswerDto.getAnswerDtoList());
        studentResult.setAnswerSheet(serializedAnswerSheet);

        studentResultService.createStudentResult(studentResult);

        // Create and return result DTO
        return createResultDto(studentResult, percentage);
    }

    private void evaluateAnswerSheetToObtainTotalAndAcquiredMark(StudentResult studentResult, List<AnswerDto> answerDtoList) throws EntityNotFoundException, com.kmea.assessme.common.exception.EntityNotFoundException {
        int totalMark = 0, acquiredMark = 0;

        for (AnswerDto answerDto : answerDtoList) {
            Questionnaire questionnaire = questionnaireService.getQuestionnaireById(answerDto.getId());
            if (answerDto.getAnswer().equalsIgnoreCase(questionnaire.getCorrectAnswer())) {
                acquiredMark += questionnaire.getDifficultyLevel().getMark();
            }
            totalMark += questionnaire.getDifficultyLevel().getMark();
        }
        studentResult.setAcquiredMark(acquiredMark);
        studentResult.setTotalMark(totalMark);
    }

    private double calculatePercentage(StudentResult studentResult) {
        return (double) studentResult.getAcquiredMark() / studentResult.getTotalMark() * 100;
    }

    private byte[] serializeAnswerDtoList(List<AnswerDto> answerDtoList) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(answerDtoList);
            return bos.toByteArray();
        }
    }

    private ResultDto createResultDto(StudentResult studentResult, double percentage) {
        ResultDto resultDto = new ResultDto();
        resultDto.setAcquiredMark(studentResult.getAcquiredMark());
        resultDto.setTotalMark(studentResult.getTotalMark());
        resultDto.setGrade(studentResult.getGrade().getGrade());
        resultDto.setStatus(percentage > 60 ? TestStatus.PASSED.toString() : TestStatus.FAILED.toString());
        return resultDto;
    }

}
