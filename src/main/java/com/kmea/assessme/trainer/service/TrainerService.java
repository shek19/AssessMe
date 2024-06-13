package com.kmea.assessme.trainer.service;

import com.kmea.assessme.common.exception.EntityNotFoundException;
import com.kmea.assessme.test.entity.Subject;
import com.kmea.assessme.trainer.entity.Trainer;
import com.kmea.assessme.trainer.entity.TrainerSubject;
import com.kmea.assessme.trainer.pojo.TrainerDto;
import com.kmea.assessme.trainer.repository.TrainerRepository;
import com.kmea.assessme.user.entity.User;
import com.kmea.assessme.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TrainerService {

    @Autowired
    TrainerRepository trainerRepository;

    @Autowired
    TrainerSubjectService trainerSubjectService;

    @Autowired
    UserService userService;

    public Trainer getEntity(TrainerDto trainerDto) {
        Trainer trainer = new Trainer();
        trainer.setName(trainerDto.getName());
        trainer.setMobile(trainerDto.getMobile());
        trainer.setQualification(trainerDto.getQualification());
        return trainer;
    }

    public Trainer createTrainer(Long id, TrainerDto trainerDto) throws EntityNotFoundException {
        Trainer trainer = trainerRepository.save(getEntity(trainerDto));
        for (Subject subject :
                trainerDto.getSubjects()) {
            TrainerSubject trainerSubject = new TrainerSubject();
            trainerSubject.setTrainer(trainer);
            trainerSubject.setSubject(subject);
            trainerSubjectService.createTrainerSubject(trainerSubject);
        }
        User user = new User();
        user.setTrainer(trainer);
        userService.updateUser(id, user);
        return trainer;
    }

}
