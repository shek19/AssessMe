package com.kmea.assessme.test.repository;

import com.kmea.assessme.test.entity.DifficultyLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DifficultyLevelRepository extends JpaRepository<DifficultyLevel, Long> {

    DifficultyLevel findByLevel(String level);

}
