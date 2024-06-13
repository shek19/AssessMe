package com.kmea.assessme.test.service;

import com.kmea.assessme.test.entity.Grade;
import com.kmea.assessme.test.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradeService {

    @Autowired
    GradeRepository gradeRepository;

    public Grade getGradeByPercentage(Integer percentage) {
        return gradeRepository.findByStartRangeLessThanEqualAndStopRangeGreaterThanEqual(percentage, percentage);
    }

}
