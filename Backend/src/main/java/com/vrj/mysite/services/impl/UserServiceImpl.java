package com.vrj.mysite.services.impl;

import com.vrj.mysite.dto.CreateUserDTO;
import com.vrj.mysite.dto.UpdateUserDTO;
import com.vrj.mysite.exceptions.UserFoundException;
import com.vrj.mysite.exceptions.UserNotFoundException;
import com.vrj.mysite.model.Rol;
import com.vrj.mysite.model.RolApplication;
import com.vrj.mysite.model.UserEntity;
import com.vrj.mysite.repositories.UserRepository;
import com.vrj.mysite.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<UserEntity> createUser(CreateUserDTO createUserDTO) throws UserFoundException {

        if(this.userRepository.findByUsername(createUserDTO.getUsername()) != null){
            throw new UserFoundException();
        }

        Set<Rol> rols = createUserDTO.getRols().stream()
                .map(role -> Rol.builder()
                        .name(RolApplication.valueOf(role))
                        .build())
                .collect(Collectors.toSet());

        UserEntity userEntity = UserEntity.builder()
                .username(createUserDTO.getUsername())
                .password(passwordEncoder.encode(createUserDTO.getPassword()))
                .email(createUserDTO.getEmail())
                .rols(rols)
                .build();

        userRepository.save(userEntity);

        return ResponseEntity.ok(userEntity);
    }

    @Override
    public ResponseEntity<UserEntity> updateUser(UpdateUserDTO updateUserDTO) throws UserNotFoundException {
        UserEntity userEntity = this.userRepository.findByUsername(updateUserDTO.getUsername());

        if(userEntity == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        if (updateUserDTO.getFirstName() != null) {
            userEntity.setFirstName(updateUserDTO.getFirstName());
        }
        if (updateUserDTO.getLastName() != null) {
            userEntity.setLastName(updateUserDTO.getLastName());
        }
        if (updateUserDTO.getEmail() != null) {
            userEntity.setEmail(updateUserDTO.getEmail());
        }
        if(updateUserDTO.getPassword() != null)
            userEntity.setPassword(passwordEncoder.encode(updateUserDTO.getPassword()));
        if(updateUserDTO.getPhone() != null){
            userEntity.setPhone(updateUserDTO.getPhone());
        }
        if (updateUserDTO.getPhoto() != null){
            userEntity.setPhoto(updateUserDTO.getPhoto());
        }

        userRepository.save(userEntity);

        return ResponseEntity.ok(userEntity);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
