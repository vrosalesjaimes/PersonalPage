package com.vrj.mysite.services.impl;

import com.vrj.mysite.dto.CreateUserDTO;
import com.vrj.mysite.dto.UpdateUserDTO;
import com.vrj.mysite.exceptions.UserFoundException;
import com.vrj.mysite.exceptions.UserNotFoundException;
import com.vrj.mysite.model.Rol;
import com.vrj.mysite.model.RolApplication;
import com.vrj.mysite.model.User;
import com.vrj.mysite.repositories.UserRepository;
import com.vrj.mysite.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<User> createUser(CreateUserDTO createUserDTO) throws UserFoundException {

        if(this.userRepository.findByUsername(createUserDTO.getUsername()) != null){
            throw new UserFoundException();
        }

        Set<Rol> rols = createUserDTO.getRols().stream()
                .map(role -> Rol.builder()
                        .name(RolApplication.valueOf(role))
                        .build())
                .collect(Collectors.toSet());

        User user = User.builder()
                .username(createUserDTO.getUsername())
                .password(createUserDTO.getPassword())
                .email(createUserDTO.getEmail())
                .rols(rols)
                .build();

        userRepository.save(user);

        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<User> updateUser(UpdateUserDTO updateUserDTO) throws UserNotFoundException {
        User user = this.userRepository.findByUsername(updateUserDTO.getUsername());

        if(user == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        if (updateUserDTO.getFirstName() != null) {
            user.setFirstName(updateUserDTO.getFirstName());
        }
        if (updateUserDTO.getLastName() != null) {
            user.setLastName(updateUserDTO.getLastName());
        }
        if (updateUserDTO.getEmail() != null) {
            user.setEmail(updateUserDTO.getEmail());
        }
        if(updateUserDTO.getPassword() != null)
            user.setPassword(updateUserDTO.getPassword());
        if(updateUserDTO.getPhone() != null){
            user.setPhone(updateUserDTO.getPhone());
        }
        if (updateUserDTO.getPhoto() != null){
            user.setPhoto(updateUserDTO.getPhoto());
        }

        userRepository.save(user);

        return ResponseEntity.ok(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
