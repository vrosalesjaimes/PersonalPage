package com.vrj.mysite.services;

import com.vrj.mysite.dto.PostDTO;
import com.vrj.mysite.exceptions.PostFoundException;
import com.vrj.mysite.exceptions.PostNotFoundException;
import com.vrj.mysite.model.Image;
import com.vrj.mysite.model.Post;
import com.vrj.mysite.model.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface PostService {

    ResponseEntity<Post> createPost(Post post) throws PostFoundException;

    ResponseEntity<String> updatePost(Long id, Post post) throws PostNotFoundException;

    ResponseEntity<String> deletePost(Long id);

    ResponseEntity<List<PostDTO>> getAll();

    ResponseEntity<Post> getById(Long id) throws PostFoundException;

    ResponseEntity<Set<PostDTO>> searchByName(String title);

    ResponseEntity<Set<PostDTO>> searchByTagName(String tagName);

    ResponseEntity<String> addImages(Long id, Set<Image> images) throws PostNotFoundException;

    ResponseEntity<String> addTags(Long id, Set<Tag> tags) throws PostNotFoundException;

}
