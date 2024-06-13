package com.kmea.assessme.user.repository;

import com.kmea.assessme.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);

    //User findById(Long id);

   // User findByUserType(String userType);
}
