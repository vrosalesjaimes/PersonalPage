package com.vrj.mysite.repositories;

import com.vrj.mysite.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {

    public Optional<Tag> findByName(String name);

}
