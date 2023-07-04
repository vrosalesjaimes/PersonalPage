package com.vrj.mysite.repositories;

import com.vrj.mysite.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    public Optional<User> findByUsername(String username);

}
