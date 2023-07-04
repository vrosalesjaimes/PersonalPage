package com.vrj.mysite.services;

import com.vrj.mysite.dto.CreateUserDTO;
import com.vrj.mysite.dto.UpdateUserDTO;
import com.vrj.mysite.exceptions.UserFoundException;
import com.vrj.mysite.exceptions.UserNotFoundException;
import com.vrj.mysite.model.User;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

public interface UserService {

    public ResponseEntity<User> createUser(CreateUserDTO createUserDTO) throws UserFoundException;

    public ResponseEntity<User> updateUser(UpdateUserDTO updateUserDTO) throws UserNotFoundException;

    public void deleteUser(Long id);
}
