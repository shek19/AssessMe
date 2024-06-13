package com.kmea.assessme.test.repository;

import com.kmea.assessme.test.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
    List<Test> findByStartTimeAfter(LocalDateTime currentDateTime);


}

