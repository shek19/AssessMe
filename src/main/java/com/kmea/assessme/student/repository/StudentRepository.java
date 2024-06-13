package com.kmea.assessme.student.repository;

import com.kmea.assessme.common.entity.Batch;
import com.kmea.assessme.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    List<Student> findByBatch(Batch batch);

}
