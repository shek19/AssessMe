package com.kmea.assessme.test.pojo;

import com.kmea.assessme.student.entity.Student;
import com.kmea.assessme.test.entity.Test;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class TestStudentDto {

    private Test test;

    private List<Student> students;

}
