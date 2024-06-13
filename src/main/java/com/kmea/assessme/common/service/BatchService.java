package com.kmea.assessme.common.service;

import com.kmea.assessme.common.entity.Batch;
import com.kmea.assessme.common.exception.EntityNotFoundException;
import com.kmea.assessme.common.repository.BatchRepository;
import com.kmea.assessme.user.entity.User;
import com.kmea.assessme.user.exception.ExistingEmailException;
import com.kmea.assessme.user.pojo.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BatchService {

    @Autowired
    BatchRepository batchRepository;

    public Batch getBatchById(Long id) throws EntityNotFoundException {
        return batchRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Batch with id " + id + " not found"));
    }

    public List<Batch> getAllBatch()
    {
        return batchRepository.findAll();
    }


    //adding and deleting batch
    public Batch createBatch(Batch batch) throws ExistingEmailException {
        Batch existingBatch = batchRepository.findByName(batch.getName());
        if (existingBatch != null) {
            throw new ExistingEmailException("Batch name already exists !");
        }
        return batchRepository.save(batch);
    }

    public String deleteBatch(Long id, String loggedInUserType) throws EntityNotFoundException {
        //Batch batch = batchRepository.findById(id);
        Batch batch = getBatchById(id);
        if (batch != null) {
            if (loggedInUserType.equals("ADMIN")) {
                batchRepository.delete(batch);
                return "Successfully deleted Batch "+batch.getName();
            } else {
                throw new IllegalArgumentException("You are not authorized to delete this user account.");
            }
        } else {
            throw new IllegalArgumentException("No such Batch exists");
        }
    }
}
