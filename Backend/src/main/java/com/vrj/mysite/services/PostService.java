package com.vrj.mysite.services;

import com.vrj.mysite.exceptions.PostFoundException;
import com.vrj.mysite.exceptions.PostNotFoundException;
import com.vrj.mysite.model.Post;
import com.vrj.mysite.model.Image;
import com.vrj.mysite.model.Tag;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface PostService {

    public ResponseEntity<Post> createPost(Long idiomId, Post post) throws PostFoundException;
    public ResponseEntity<String> updatePost(Long id, Post post) throws PostNotFoundException;
    public ResponseEntity<String> deletePost(Long id);
    public ResponseEntity<Set<Post>> getAllByIdiomId(Long idiomId);
    public ResponseEntity<Post> getById(Long id) throws PostFoundException;
    public ResponseEntity<Set<Post>> searchByNameAndIdiomId(String title, Long idiomId);
    public ResponseEntity<Set<Post>> searchByTagNameAndIdiomId(String tagName, Long idiomId);
    public ResponseEntity<String> addImages(Long id, Set<Image> images) throws PostNotFoundException;
    public ResponseEntity<String> addTags(Long id, Set<Tag> tags) throws PostNotFoundException;

}
