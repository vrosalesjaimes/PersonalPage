package com.vrj.mysite.repositories;

import com.vrj.mysite.model.Idiom;
import com.vrj.mysite.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PostRepository extends JpaRepository<Post, Long> {

    public Post findByTitle(String title);

    public Set<Post> findAllByIdiom(Idiom idiom);

}
