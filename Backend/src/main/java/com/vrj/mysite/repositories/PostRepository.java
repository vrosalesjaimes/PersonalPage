package com.vrj.mysite.repositories;

import com.vrj.mysite.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByTitleAndIdiom_id(String title, Long idiom_id);

    Set<Post> findAllByIdiomId(Long idiomId);

    Set<Post> findAllByTitleContainingIgnoreCaseAndIdiom_Id(String title, Long idiomId);

    Set<Post> findAllByTags_NameContainingIgnoreCaseAndIdiom_Id(String tagName, Long idiomId);

}
