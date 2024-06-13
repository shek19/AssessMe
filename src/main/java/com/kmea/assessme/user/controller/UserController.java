package com.kmea.assessme.user.controller;

import com.kmea.assessme.common.entity.Batch;
import com.kmea.assessme.common.exception.EntityNotFoundException;
import com.kmea.assessme.common.service.BatchService;
import com.kmea.assessme.user.entity.User;
import com.kmea.assessme.user.exception.ExistingEmailException;
import com.kmea.assessme.user.exception.InvalidCredentialException;
import com.kmea.assessme.user.pojo.UserDto;
import com.kmea.assessme.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    BatchService batchService;

    @GetMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.getUserByIdReturnPojo(id)); // Return HTTP 200 OK
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping(value = "/users/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> saveUser(@RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.createUser(user));
        } catch (ExistingEmailException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    // Sign-in endpoint for existing users
    @GetMapping("/users/sign-in")
    public ResponseEntity<UserDto> signIn(@RequestParam String email, @RequestParam String password) {
        try {
            return ResponseEntity.ok(userService.login(email, password)); // Return HTTP 200 OK
        } catch (InvalidCredentialException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Return HTTP 401 Unauthorized
        }
    }

    @PutMapping("/users/change-password/{email}")
    public void passwordReset(@PathVariable String email, @RequestBody Map<String, String> requestBody) throws EntityNotFoundException {
        String newPassword = requestBody.get("newPassword");
        userService.passwordReset(email,newPassword);
    }

    @PatchMapping(value = "/users/password-reset", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> passwordReset(@RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.passwordReset(user.getEmail(), user.getPassword())); // Return HTTP 200 OK
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PatchMapping(value = "/users/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.updateUser(id, user)); // Return HTTP 200 OK
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(value = "/users/email/{email}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserByEmail(@PathVariable String email){
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    //adding and deleting new batch
    @PostMapping(value = "/batch/add-new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Batch> saveBatch(@RequestBody Batch batch) {
        try {
            return ResponseEntity.ok(batchService.createBatch(batch));
        } catch (ExistingEmailException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @DeleteMapping("/batch/delete/{id}")
    public String deleteUser(@PathVariable Long id, @RequestParam String loggedInUserType) throws EntityNotFoundException {
        return batchService.deleteBatch(id, loggedInUserType);
    }

}
