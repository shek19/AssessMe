package com.kmea.assessme.user.pojo;

import com.kmea.assessme.student.entity.Student;
import com.kmea.assessme.trainer.entity.Trainer;
import com.kmea.assessme.user.enumeration.UserType;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserDto {

    private Long id;

    private String email;

    private UserType userType;

    private Trainer trainer;

    private Student student;

}
