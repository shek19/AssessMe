package com.kmea.assessme.common.repository;

import com.kmea.assessme.common.entity.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchRepository extends JpaRepository<Batch,Long> {
    Batch findByName(String name);

}
