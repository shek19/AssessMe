package com.kmea.assessme.user.service;

import com.kmea.assessme.common.exception.EntityNotFoundException;
import com.kmea.assessme.user.entity.User;
import com.kmea.assessme.user.exception.ExistingEmailException;
import com.kmea.assessme.user.exception.InvalidCredentialException;
import com.kmea.assessme.user.pojo.UserDto;
import com.kmea.assessme.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserDto getPojo(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setUserType(user.getUserType());
        userDto.setTrainer(user.getTrainer());
        userDto.setStudent(user.getStudent());
        return userDto;
    }

    public UserDto createUser(User user) throws ExistingEmailException {
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null) {
            throw new ExistingEmailException("Email already exists !");
        }
        userRepository.save(user);
        return getPojo(user);
    }

    public UserDto login(String email, String password) throws InvalidCredentialException {
        User user = getUserByEmail(email);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                return getPojo(user);
            } else {
                throw new InvalidCredentialException("Invalid password");
            }
        } else {
            throw new InvalidCredentialException("Invalid username or email");
        }
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserDto passwordReset(String email, String newPassword) throws EntityNotFoundException {
        User user = getUserByEmail(email);
        if (user != null) {
            user.setPassword(newPassword);
            userRepository.save(user);
            return getPojo(user);
        } else {
            throw new EntityNotFoundException("User not found with email : " + email);
        }
    }

    public UserDto updateUser(Long id, User userRequest) throws EntityNotFoundException {
        User user = getUserById(id);
        if (userRequest.getEmail() != null) user.setEmail(userRequest.getEmail());
        if (userRequest.getPassword() != null) user.setPassword(userRequest.getPassword());
        if (userRequest.getUserType() != null) user.setUserType(userRequest.getUserType());
        if (userRequest.getTrainer() != null) user.setTrainer(userRequest.getTrainer());
        if (userRequest.getStudent() != null) user.setStudent(userRequest.getStudent());
        return getPojo(userRepository.save(user));
    }

    public User getUserById(Long id) throws EntityNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
    }

    public UserDto getUserByIdReturnPojo(Long id) throws EntityNotFoundException {
        return getPojo(getUserById(id));
    }

}
