package com.kmea.assessme.test.entity;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "test")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "test_name")
    private String testName;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Column(name = "conditions")
    private String conditions;

    @Timestamp
    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "duration_in_minutes")
    private Integer durationInMinutes;

}
