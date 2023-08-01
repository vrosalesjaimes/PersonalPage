package com.vrj.mysite.services;

import com.vrj.mysite.dto.UpdateUserDTO;
import com.vrj.mysite.exceptions.UserFoundException;
import com.vrj.mysite.exceptions.UserNotFoundException;
import com.vrj.mysite.model.UserEntity;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<UserEntity> createUser(CreateUserDTO createUserDTO) throws UserFoundException;

    ResponseEntity<UserEntity> updateUser(UpdateUserDTO updateUserDTO) throws UserNotFoundException;

    void deleteUser(Long id);
}
