package com.vrj.mysite.repositories;

import com.vrj.mysite.model.About;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AboutRepository extends JpaRepository<About, Long> {
    Optional<About> findByIdiom_id(Long id);

    Optional<About> findByName(String name);

}
