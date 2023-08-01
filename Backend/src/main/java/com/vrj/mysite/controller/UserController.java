package com.vrj.mysite.controller;

import com.vrj.mysite.dto.UpdateUserDTO;
import com.vrj.mysite.exceptions.UserFoundException;
import com.vrj.mysite.exceptions.UserNotFoundException;
import com.vrj.mysite.model.UserEntity;
import com.vrj.mysite.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create-user")
    public ResponseEntity<UserEntity> createUser(@RequestBody CreateUserDTO createUserDTO) throws UserFoundException {
        return userService.createUser(createUserDTO);
    }

    @PostMapping("/update-user")
    public ResponseEntity<UserEntity> updateUser(@RequestBody UpdateUserDTO updateUserDTO) throws UserNotFoundException {
        return userService.updateUser(updateUserDTO);
    }

    @DeleteMapping("/delete-user/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }


}