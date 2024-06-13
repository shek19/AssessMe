package com.kmea.assessme.test.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class AnswerDto implements Serializable {

    private Long id;

    private String question;

    private String answer;

}
