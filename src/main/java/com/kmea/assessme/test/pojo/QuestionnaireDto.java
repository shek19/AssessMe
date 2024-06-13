package com.kmea.assessme.test.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QuestionnaireDto {

    private Long id;

    private String question;

    private String option1;

    private String option2;

    private String option3;

    private String option4;

    private Integer marks;

}
