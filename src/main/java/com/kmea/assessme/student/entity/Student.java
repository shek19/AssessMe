package com.kmea.assessme.student.entity;

import com.kmea.assessme.common.entity.Batch;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String mobile;

    private String email;

    @ManyToOne
    @JoinColumn(name = "batch_id")
    private Batch batch;

}
