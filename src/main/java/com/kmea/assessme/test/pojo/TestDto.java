package com.kmea.assessme.test.pojo;

import com.kmea.assessme.test.entity.Subject;
import com.kmea.assessme.test.entity.TestChapter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
public class TestDto {

    private Long id;

    private String testName;

    private Subject subject;

    private List<TestChapter> testChapters;

    private String conditions;

    private LocalDateTime startTime;

    private Integer durationInMinutes;

}
