package com.kmea.assessme.user.entity;

import com.kmea.assessme.student.entity.Student;
import com.kmea.assessme.trainer.entity.Trainer;
import com.kmea.assessme.user.enumeration.UserType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type")
    private UserType userType;

    @OneToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

}