package com.vrj.mysite.services;

import com.vrj.mysite.exceptions.PostFoundException;
import com.vrj.mysite.exceptions.PostNotFoundException;
import com.vrj.mysite.model.Post;
import org.springframework.http.ResponseEntity;

public interface PostService {

    public ResponseEntity<Post> createPost(Post post) throws PostFoundException;
    public ResponseEntity<Post> updatePost(Long id, Post post) throws PostNotFoundException;
    public void deletePost(Long id);

}