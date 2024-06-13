package com.kmea.assessme.test.repository;

import com.kmea.assessme.test.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {

    Grade findByStartRangeLessThanEqualAndStopRangeGreaterThanEqual(Integer percentage, Integer percentage1);

}
