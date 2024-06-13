package com.kmea.assessme.test.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "grade")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "grade")
    private Character grade;

    @Column(name = "start_range")
    private Integer startRange;

    @Column(name = "stop_range")
    private Integer stopRange;

}
