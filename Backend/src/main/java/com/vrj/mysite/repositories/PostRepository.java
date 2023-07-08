package com.vrj.mysite.repositories;

import com.vrj.mysite.model.Post;
import com.vrj.mysite.model.Idiom;
import com.vrj.mysite.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface PostRepository extends JpaRepository<Post, Long> {

    public Optional<Post> findByTitle(String title);
    public Set<Post> findAllByIdiom(Idiom idiom);
    public Set<Post> findAllByTitleContainingIgnoreCaseAndIdiom_Id(String title, Long idiomId);
    public Set<Post> findAllByTags_NameContainingIgnoreCaseAndIdiom_Id(String tagName, Long idiomId);

}