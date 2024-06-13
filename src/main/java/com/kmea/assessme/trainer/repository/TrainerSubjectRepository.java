package com.kmea.assessme.trainer.repository;

import com.kmea.assessme.test.entity.Subject;
import com.kmea.assessme.trainer.entity.Trainer;
import com.kmea.assessme.trainer.entity.TrainerSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainerSubjectRepository extends JpaRepository<TrainerSubject, Long> {

    List<TrainerSubject> findByTrainer(Trainer trainer);

    List<TrainerSubject> findBySubject(Subject subject);

}
