package com.kmea.assessme.trainer.pojo;

import com.kmea.assessme.test.entity.Subject;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class TrainerDto {

    private Long id;

    private String name;

    private String mobile;

    private String qualification;

    private List<Subject> subjects;

}
