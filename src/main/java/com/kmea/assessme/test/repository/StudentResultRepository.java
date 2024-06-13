package com.kmea.assessme.test.repository;

import com.kmea.assessme.test.entity.StudentResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentResultRepository extends JpaRepository<StudentResult,Long> {
}
