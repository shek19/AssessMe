package com.kmea.assessme.trainer.service;

import com.kmea.assessme.test.entity.Subject;
import com.kmea.assessme.trainer.entity.Trainer;
import com.kmea.assessme.trainer.entity.TrainerSubject;
import com.kmea.assessme.trainer.repository.TrainerSubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerSubjectService {

    @Autowired
    TrainerSubjectRepository trainerSubjectRepository;
    public TrainerSubject createTrainerSubject(TrainerSubject trainerSubject) {
        return trainerSubjectRepository.save(trainerSubject);
    }

    public List<TrainerSubject> getAllTrainerSubjectsByTrainer(Trainer trainer){
        return trainerSubjectRepository.findByTrainer(trainer);
    }

    public List<TrainerSubject> getAllTrainerSubjectsBySubject(Subject subject){
        return trainerSubjectRepository.findBySubject(subject);
    }

}
