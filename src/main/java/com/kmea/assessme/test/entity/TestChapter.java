package com.kmea.assessme.test.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "test_chapter")
public class TestChapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

    @ManyToOne
    @JoinColumn(name = "chapter_id")
    private Chapter chapter;

    @Column(name = "easy_questions")
    private Integer easyQuestions;

    @Column(name = "difficult_questions")
    private Integer difficultQuestions;

    @Column(name = "complex_questions")
    private Integer complexQuestions;

}
