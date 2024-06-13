package com.kmea.assessme.trainer.controller;

import com.kmea.assessme.common.exception.EntityNotFoundException;
import com.kmea.assessme.trainer.entity.Trainer;
import com.kmea.assessme.trainer.pojo.TrainerDto;
import com.kmea.assessme.trainer.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TrainerController {

    @Autowired
    TrainerService trainerService;

    @PostMapping(value = "/users/{id}/trainers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Trainer> createTrainer(@PathVariable Long id, @RequestBody TrainerDto trainerDto) {
        try {
            return ResponseEntity.ok(trainerService.createTrainer(id, trainerDto));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
