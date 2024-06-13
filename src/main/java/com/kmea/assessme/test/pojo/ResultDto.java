package com.kmea.assessme.test.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResultDto {

    private Integer acquiredMark;

    private Integer totalMark;

    private Character grade;

    private String status;

}
