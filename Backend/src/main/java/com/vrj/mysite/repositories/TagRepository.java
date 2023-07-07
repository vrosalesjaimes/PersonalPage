package com.vrj.mysite.repositories;

import com.vrj.mysite.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
