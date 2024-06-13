package com.kmea.assessme.test.repository;

import com.kmea.assessme.test.entity.TestStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestStudentRepository extends JpaRepository<TestStudent, Long> {
}
