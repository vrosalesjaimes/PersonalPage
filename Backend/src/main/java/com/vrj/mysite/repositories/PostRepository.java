package com.vrj.mysite.repositories;

import com.vrj.mysite.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByTitle(String title);

    List<Post> findAll();

    Set<Post> findAllByTitleContainingIgnoreCase(String title);

    Set<Post> findAllByTags_NameContainingIgnoreCase(String tagName);

}
