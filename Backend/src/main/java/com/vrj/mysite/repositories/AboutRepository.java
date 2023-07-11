package com.vrj.mysite.repositories;

import com.vrj.mysite.model.About;
import com.vrj.mysite.model.Idiom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AboutRepository extends JpaRepository<About, Long> {
    public Optional<About> findByIdiom_id(Long id);
    public Optional<About> findByName(String name);

}
